package com.delta.rental.deltarental.entities.concretes;

import com.delta.rental.deltarental.entities.abstracts.BaseEntity;
import com.delta.rental.deltarental.entities.concretes.Car;
import com.delta.rental.deltarental.entities.concretes.Customer;
import com.delta.rental.deltarental.entities.concretes.Employee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental extends BaseEntity {

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "start_kilometer")
    private double startKilometer;

    @Column(name = "end_kilometer")
    private Double endKilometer;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "discount")
    private double discount;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "return_location")
    private String returnLocation;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}
