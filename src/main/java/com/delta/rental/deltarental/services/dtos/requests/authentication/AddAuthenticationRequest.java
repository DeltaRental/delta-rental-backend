package com.delta.rental.deltarental.services.dtos.requests.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAuthenticationRequest {
    private String email;
    String password;
}
