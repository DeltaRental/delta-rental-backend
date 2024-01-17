package com.delta.rental.deltarental.services.dtos.requests.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    private String name;

    private String surname;

    private String gsm;

    private String email;

    private String password;
}
