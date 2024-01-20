package com.delta.rental.deltarental.services.dtos.requests.user;


import com.delta.rental.deltarental.enums.UserRole;
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


    @NotNull(message = "İsim alanı boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "İsim alanı sadece harflerden oluşmalıdır.")
    private String name;

    @NotNull(message = "Soyisim alanı boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Soyisim alanı sadece harflerden oluşmalıdır.")
    private String surname;

    @Pattern(regexp = "^[0-9]{10}$", message = "Telefon numarasını başında sıfır olmadan giriniz!.")
    private String gsm;

    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Parola en az 8 karakter uzunluğunda olmalı, en az bir harf, bir rakam ve bir özel karakter içermelidir.")
    private String password;

    private List<UserRole> roles;
}
