package com.delta.rental.deltarental.controllers;


import com.delta.rental.deltarental.repositories.InvoiceRepository;
import com.delta.rental.deltarental.services.abstracts.InvoiceService;
import com.delta.rental.deltarental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.delta.rental.deltarental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.delta.rental.deltarental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.delta.rental.deltarental.services.dtos.responses.invoice.GetInvoiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/invoices")
@CrossOrigin
public class InvoicesController {
    private final InvoiceService invoiceService;

    @GetMapping("{id}")
    public GetInvoiceResponse getById(int id){
        return invoiceService.getById(id);
    }
    @GetMapping("/getAll")
    public List<GetInvoiceListResponse> getAll(){
        return invoiceService.getAll();
    }
    @PostMapping()
    public void add(@RequestBody @Valid AddInvoiceRequest addInvoiceRequest){
        invoiceService.add(addInvoiceRequest);
    }

    @PutMapping()
    public void update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest){
        invoiceService.update(updateInvoiceRequest);
    }


    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        invoiceService.delete(id);
    }

    @GetMapping("/getAllInvoiceDetails")
    public List<GetInvoiceListResponse> getAllInvoiceDetails(int id){
        return invoiceService.getAllInvoiceDetails(id);
    }

    @GetMapping("/getInvoiceDetails")
    public List<GetInvoiceListResponse> getInvoiceDetails(int id){
        return invoiceService.getInvoiceDetails(id);
    }
}
