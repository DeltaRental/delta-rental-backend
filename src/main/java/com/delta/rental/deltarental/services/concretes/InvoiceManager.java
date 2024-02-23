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
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalListResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import com.delta.rental.deltarental.services.rules.InvoiceBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class InvoiceManager implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceBusinessRules invoiceBusinessRules;
    private final ModelMapperService modelMapperService;
    private final RentalService rentalService;
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

        GetRentalResponse getRentalResponse = rentalService.getById(addInvoiceRequest.getRentalId());
        invoice.setDate(getRentalResponse.getStartDate());
        invoice.setAmount(getRentalResponse.getTotalPrice());
        invoice.setName(getRentalResponse.getCustomer().getUserName());
        invoice.setAddress(getRentalResponse.getCustomer().getNationalityId());

        //rentalService.matchByRentalDateToInvoiceDate(addInvoiceRequest.getRentalId(), invoice.getDate());

        invoiceRepository.save(invoice);
    }

    @Override
    public void update(UpdateInvoiceRequest updateInvoiceRequest) {



        Invoice invoice = this.modelMapperService.forRequest()
                .map(updateInvoiceRequest, Invoice.class);



        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(int id) {
        invoiceRepository.deleteById(id);

    }

    @Override
    public List<GetInvoiceListResponse> getAllInvoiceDetails(int id) {
        List<Invoice> invoices = invoiceRepository.findAllInvoiceDetails(id);
        List<GetInvoiceListResponse> showInvoice = invoices.stream()
                .map(invoice ->this.modelMapperService.forResponse()
                        .map(invoice, GetInvoiceListResponse.class)).collect(Collectors.toList());
        return showInvoice;
    }

    @Override
    public List<GetInvoiceListResponse> getInvoiceDetails(String plate) {
        List<Invoice> invoices = invoiceRepository.findInvoiceDetails(plate);
        List<GetInvoiceListResponse> showInvoice = invoices.stream()
                .map(invoice ->this.modelMapperService.forResponse()
                        .map(invoice, GetInvoiceListResponse.class)).collect(Collectors.toList());
        return showInvoice;
    }


}
