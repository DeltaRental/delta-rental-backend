package com.delta.rental.deltarental.services.dtos.requests.user;


import com.delta.rental.deltarental.enums.UserRole;
import com.delta.rental.deltarental.services.constants.Messages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotNull(message = Messages.UserMessages.USER_NAME_NOT_BLANK)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = Messages.UserMessages.USER_NAME_ONLY_LETTERS)
    private String name;

    @NotNull(message = Messages.UserMessages.USER_SURNAME_NOT_BLANK)
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = Messages.UserMessages.USER_SURNAME_ONLY_LETTERS)
    private String surname;

    @Pattern(regexp = "^[0-9]{10}$", message = Messages.UserMessages.PHONE_NUMBER_NOT_START_ZERO)
    private String gsm;

    @Email(message = Messages.UserMessages.ENTER_VALID_EMAIL)
    private String email;

    //@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Parola en az 8 karakter uzunluğunda olmalı, en az bir harf, bir rakam ve bir özel karakter içermelidir.")
    private String password;

    private List<UserRole> authorities;
}
