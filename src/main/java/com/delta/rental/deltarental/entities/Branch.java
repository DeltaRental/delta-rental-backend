package com.delta.rental.deltarental.entities;


import com.delta.rental.deltarental.enums.City;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "branches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "gsm")
    private String gsm;

    @Column(name = "email")
    private String email;

    @Column(name = "manager_name")
    private String managerName;

    @Column(name = "post_code")
    private String postCode;

    @Column(name = "city")
    @Enumerated(EnumType.STRING)
    private City city;

    //@ManyToOne
    //@JoinColumn(name = "city_id")
    //private com.delta.rental.deltarental.entities.City city;

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private List<Car> cars;



}
