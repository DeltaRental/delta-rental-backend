package com.delta.rental.deltarental.services.dtos.responses.city;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityResponse {
    private int id;
    private String name;
}
