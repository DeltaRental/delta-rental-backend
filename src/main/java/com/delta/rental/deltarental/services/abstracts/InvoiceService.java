package com.delta.rental.deltarental.services.abstracts;


import com.delta.rental.deltarental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.delta.rental.deltarental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.delta.rental.deltarental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.delta.rental.deltarental.services.dtos.responses.invoice.GetInvoiceResponse;

import java.util.List;

public interface InvoiceService {

    GetInvoiceResponse getById(int id);

    List<GetInvoiceListResponse> getAll();

    void add(AddInvoiceRequest addInvoiceRequest);

    void update(UpdateInvoiceRequest updateInvoiceRequest);

    void delete(int id);


    List<GetInvoiceListResponse> getAllInvoiceDetails(int id);

    List<GetInvoiceListResponse> getInvoiceDetails(int id);
}
