package com.delta.rental.deltarental.repositories;

import com.delta.rental.deltarental.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
