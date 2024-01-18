package com.delta.rental.deltarental.services.dtos.requests.user;


import com.delta.rental.deltarental.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<UserRole> roles;
}
