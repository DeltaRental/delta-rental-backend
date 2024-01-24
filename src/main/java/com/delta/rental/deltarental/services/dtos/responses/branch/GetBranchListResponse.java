package com.delta.rental.deltarental.services.dtos.responses.branch;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetBranchListResponse {
    private int id;

    private String name;

    private String address;

    private String gsm;

    private String email;

    private String managerName;

    private String postCode;
}
