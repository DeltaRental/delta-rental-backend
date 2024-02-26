package com.delta.rental.deltarental.entities.concretes;

import com.delta.rental.deltarental.entities.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "amount")
    private double amount;


    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
