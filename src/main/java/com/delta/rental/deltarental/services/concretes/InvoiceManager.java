package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.Invoice;
import com.delta.rental.deltarental.entities.concretes.Rental;
import com.delta.rental.deltarental.repositories.InvoiceRepository;
import com.delta.rental.deltarental.services.abstracts.InvoiceService;
import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.delta.rental.deltarental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.delta.rental.deltarental.services.dtos.responses.invoice.GetInvoiceResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import com.delta.rental.deltarental.services.rules.InvoiceBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final RentalService rentalService;
    private final InvoiceBusinessRules invoiceBusinessRules;
    private final ModelMapperService modelMapperService;
    @Override
    public GetInvoiceResponse getById(int id) {
        Invoice invoice = invoiceBusinessRules.checkByInvoiceId(id);
        GetInvoiceResponse invoiceResponse = modelMapperService.forResponse().map(invoice, GetInvoiceResponse.class);
        return invoiceResponse;
    }

    @Override
    public List<GetInvoiceListResponse> getAll() {
        List<Invoice> invoiceList = invoiceRepository.findAll();

        List<GetInvoiceListResponse> invoiceResponse = invoiceList.stream()
                .map(invoice ->this.modelMapperService.forResponse()
                        .map(invoice, GetInvoiceListResponse.class)).collect(Collectors.toList());

        return invoiceResponse;
    }

    @Override
    public void add(AddInvoiceRequest addInvoiceRequest) {


        Invoice invoice = this.modelMapperService.forRequest()
                .map(addInvoiceRequest, Invoice.class);

        GetRentalResponse rentalId = rentalService.getById(addInvoiceRequest.getRentalId());

        //invoiceBusinessRules.checkByDateIsBeforeInvoiceDate(addInvoiceRequest.getDate());
        invoiceBusinessRules.checkByRentalId(addInvoiceRequest.getRentalId());
        invoiceBusinessRules.matchByRentalReturnDateToInvoiceDate(rentalId.getReturnDate());

        invoiceRepository.save(invoice);
    }

    @Override
    public void update(UpdateInvoiceRequest updateInvoiceRequest) {



        Invoice invoice = this.modelMapperService.forRequest()
                .map(updateInvoiceRequest, Invoice.class);

        invoiceBusinessRules.checkByDateIsBeforeInvoiceDate(updateInvoiceRequest.getDate());
        invoiceBusinessRules.checkByRentalId(updateInvoiceRequest.getRentalId());


        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(int id) {
        invoiceRepository.deleteById(id);

    }
}
