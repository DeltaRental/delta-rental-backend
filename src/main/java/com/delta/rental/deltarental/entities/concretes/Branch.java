package com.delta.rental.deltarental.entities.concretes;


import com.delta.rental.deltarental.entities.abstracts.BaseEntity;
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
public class Branch extends BaseEntity {

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
