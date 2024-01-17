package com.delta.rental.deltarental.services.dtos.responses.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAuthenticationResponse {
    private String token;
}
