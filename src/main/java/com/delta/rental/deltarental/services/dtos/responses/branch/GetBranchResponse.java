package com.delta.rental.deltarental.services.dtos.responses.branch;


import com.delta.rental.deltarental.enums.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBranchResponse {
    private int id;

    private String name;

    private String address;

    private String gsm;

    private String email;

    private String managerName;

    private String postCode;

    private City city;
}
