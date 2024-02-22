package com.delta.rental.deltarental.services.rules;


import com.delta.rental.deltarental.entities.concretes.Invoice;
import com.delta.rental.deltarental.repositories.InvoiceRepository;
import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class InvoiceBusinessRules {
    private final RentalService rentalService;
    private final InvoiceRepository invoiceRepository;

    //DB içerisinde invoice id' ye göre invoice olup olmama durumu kontrolü
    public Invoice checkByInvoiceId(int id){
        if(!(invoiceRepository.existsById(id))){
            throw new RuntimeException(id + Messages.InvoiceMessages.INVOICE_NOT_FOUND);
        }
        return invoiceRepository.findById(id).orElseThrow();
    }

    //Fatura tarihi bugünden daha geçmiş bir tarih olma durumu kontrolü
    public void checkByDateIsBeforeInvoiceDate(LocalDate invoiceDate){
        if(invoiceDate.isBefore(LocalDate.now())){
            throw new RuntimeException(Messages.InvoiceMessages.INVOICE_DATE_NOT_LESS_THAN_NOW_DATE);
        }
    }

    //Rental id' nin db' de var olup olamama durumu kontrolü.
    public void checkByRentalId(int id){
        rentalService.getById(id);
    }

    //Rental'de Return date'in invoice date olarak belirlenmesi
    public void matchByRentalReturnDateToInvoiceDate(LocalDate returnDate){
        Invoice invoice =new Invoice();
        invoice.setDate(returnDate);
    }
}
