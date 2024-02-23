package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    boolean existsById(int id);


    @Query(nativeQuery = true,value = "select i.* from invoices i inner join rentals r on i.rental_id = r.id  inner join customers cus on cus.id = r.customer_id where cus.user_id = ?1")
    List<Invoice> findAllInvoiceDetails(int id);

    @Query(nativeQuery = true,value = "select * from invoices i where i.rental_id = ?1")
    List<Invoice> findInvoiceDetails(int id);

}
