package com.delta.rental.deltarental.entities.concretes;

import com.delta.rental.deltarental.entities.abstracts.BaseEntity;
import com.delta.rental.deltarental.enums.CarState;
import com.delta.rental.deltarental.enums.FuelType;
import com.delta.rental.deltarental.enums.GearType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "cars")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {

    @Column(name = "kilometer")
    private double kilometer;

    @Column(name = "year")
    private int year;

    @Column(name = "daily_price")
    private double dailyPrice;

    @Column(name = "plate")
    private String plate;

    @Column(name="location")
    private String location;

    @Column(name = "gear_type")
    @Enumerated(EnumType.STRING)
    private GearType gearType;

    @Column(name = "car_state")
    @Enumerated(EnumType.STRING)
    private CarState carState;

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;


    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    /*@Column(name="image_url")
    private String imageUrl;*/

    @OneToMany(mappedBy = "car")
    @JsonIgnore
    private List<Rental> Rentals;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}
