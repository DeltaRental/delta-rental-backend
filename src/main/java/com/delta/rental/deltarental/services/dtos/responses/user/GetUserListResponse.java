package com.delta.rental.deltarental.services.dtos.responses.user;

import com.delta.rental.deltarental.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse {

    private int id;

    private String name;

    private String surname;

    private String gsm;

    private String email;

    private List<UserRole> authorities;
}
