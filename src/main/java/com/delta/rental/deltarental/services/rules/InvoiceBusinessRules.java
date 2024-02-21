package com.delta.rental.deltarental.services.rules;


import com.delta.rental.deltarental.entities.concretes.Invoice;
import com.delta.rental.deltarental.repositories.InvoiceRepository;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final InvoiceRepository invoiceRepository;

    //DB içerisinde invoice id' ye göre invoice olup olmama durumu kontrolü
    public Invoice checkByInvoiceId(int id){
        if(!(invoiceRepository.existsById(id))){
            throw new RuntimeException(id + Messages.InvoiceMessages.INVOICE_NOT_FOUND);
        }
        return invoiceRepository.findById(id).orElseThrow();
    }
}
