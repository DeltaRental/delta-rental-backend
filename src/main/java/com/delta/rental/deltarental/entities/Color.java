package com.delta.rental.deltarental.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "colors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Color {
    public Color(int colorId) {
        this.id = colorId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "color")
    @JsonIgnore
    private List<Car> cars;
}
