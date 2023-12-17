package com.delta.rental.deltarental.services.dtos.responses.model;

import com.delta.rental.deltarental.entities.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetModelListResponse {

    private int id;

    private String name;

    private String brandName;

}
