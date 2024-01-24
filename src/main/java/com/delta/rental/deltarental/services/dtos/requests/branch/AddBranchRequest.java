package com.delta.rental.deltarental.services.dtos.requests.branch;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBranchRequest {
    @NotNull(message = "Şube isim alanı boş geçilemez")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Şube isim alanı sadece harflerden oluşmalıdır.")
    private String name;

    @NotBlank(message = "Şehir adı boş olamaz!")
    @Length(max=255,message = "Maksimum sınıra ulaştınız.")
    private String address;

    @Pattern(regexp = "^[0-9]{10}$", message = "Telefon numarasını başında sıfır olmadan giriniz!.")
    private String gsm;

    @Email(message = "Geçerli bir e-posta adresi giriniz .")
    private String email;

    @NotBlank(message = "Şehir adı boş olamaz!")
    @Length(min = 2,message = "Girilen Şehir adı en az 2 harfli olmalıdır.")
    private String managerName;

    @NotBlank(message = "Posta Kodu boş olamaz!")
    @Length(min=5,max = 5,message = "Posta kodu 5 basamaklı olmalıdır!")
    private String postCode;

    @Positive(message = "City id 0' dan küçük bir değer olamaz.")
    private int cityId;
}
