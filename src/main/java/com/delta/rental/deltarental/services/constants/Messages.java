package com.delta.rental.deltarental.services.constants;

public class Messages {

    public class GeneralMessages{
        public static final String WRONG_INFORMATION = "Bilgiler hatalı!";
        public static final String REPLACE_ALL_REGEX = "\\s";
        public static final String REPLACE_ALL_REPLACEMENT = "";
    }

    public class IdMessages{
        public static final String ID_NOT_NULL = "Id boş geçilemez";
        public static final String ID_NOT_NEGATIVE = "Id 0'dan küçük olamaz";
    }

    public class BranchMessages{
        public static final String BRANCH_NAME_NOT_NULL = "Şube isim alanı boş geçilemez";
        public static final String BRANC_NAME_ONLY_LETTERS = "Şube isim alanı sadece harflerden oluşmalıdır.";
        public static final String ADDRESS_NOT_BLANK = "Adres boş olamaz!";
        public static final String MAX_LENGTH = "Maksimum sınıra ulaştınız.";
        public static final String PHONE_NUMBER_NOT_BEGIN_ZERO = "Telefon numarasını başında sıfır olmadan giriniz!.";
        public static final String ENTER_VALID_EMAIL = "Geçerli bir e-posta adresi giriniz.";
        public static final String MANAGER_NAME_NOT_BLANK = "Müdür adı boş olamaz!";
        public static final String MANAGER_NAME_LENGTH_MIN_TWO_LETTERS = "Girilen Müdür adı en az 2 harfli olmalıdır.";
        public static final String POSTAL_CODE_NOT_BLANK  = "Posta Kodu boş olamaz!";
        public static final String POSTAL_CODE_LENGTH_FIVE_DIGITS = "Posta kodu 5 basamaklı olmalıdır!";
        public static final String BRANCH_NOT_FOUND = " nolu id'ye sahip Şube bulunmamaktadır.";
        public static final String SAME_BRANCH_NAME_EXISTS = "Bu Şube adı zaten var!!";
    }

    public class BrandMessages{
        public static final String BRAND_NAME_NOT_BLANK = "Marka adı boş olamaz!";
        public static final String BRAND_NAME_LENGTH_MIN_TWO_LETTERS = "Girilen marka en az 2 harfli olmalıdır.";
        public static final String BRAND_NOT_FOUND = " nolu id'ye sahip marka bulunmamaktadır.";
        public static final String SAME_BRAND_NAME_EXISTS = "Bu araba markası zaten var!!";
    }

    public class CarMessages{
       public static final String KILOMETER_NOT_NULL = "Kilometre boş geçilemez.";
        public static final String KILOMETER_NOT_NEGATIVE = "Kilometre 0'dan küçük olamaz.";
        public static final String YEAR_NOT_NULL = "Yıl boş geçilemez";
        public static final String YEAR_NOT_LESS_THAN_TWO_THOUSAND_FIVE = "Yıl 2005'den küçük olamaz.";
        public static final String YEAR_NOT_GREATER_THAN_TWO_THOUSAND_TWENTY_FOUR = "Yıl 2024'den büyük olamaz.";
        public static final String DAILY_PRICE_NOT_NEGATIVE = "Günlük kiralama ücreti 0'dan küçük olamaz.";
        public static final String ENTER_VALID_PLATE = "geçerli bir plaka giriniz.";
        public static final String SAME_PLATE_CAR_EXISTS = "Aynı plakada başka bir araç eklenemez.";
        public static final String CAR_NOT_FOUND = " nolu id'ye sahip araç bulunmamaktadır.";

    }

    public class ColorMessages{
        public static final String COLOR_NOT_BLANK = "Renk boş olamaz!";
        public static final String COLOR_LENGTH_MIN_TWO_LETTERS = "Girilen renk en az 2 harfli olmalıdır.";
        public static final String COLOR_NOT_FOUND = " nolu id'ye sahip renk bulunmamaktadır.";
        public static final String SAME_COLOR_EXISTS = "Bu renk zaten var!!";


    }

    public class CustomerMessages{
        public static final String CUSTOMER_NOT_FOUND = " nolu id'ye sahip müşteri bulunmamaktadır.";
        public static final String SAME_CUSTOMER_NATIONALITY_ID_EXISTS = " bu kimlik numarası sistemde mevcuttur.";
        public static final String CUSTOMER_NATIONALITY_ID_NOT_NULL = "Kimlik numarası boş geçilemez";
        public static final String CUSTOMER_NATIONALITY_ID_LENGTH_ELEVEN_DIGITS = "Kimlik numarası 11 haneli olmak zorundadır.";
        public static final String CUSTOMER_NATIONALITY_ID_ONLY_NUMBERS = "Kimlik numarası sadece rakamlardan oluşmalıdır.";
    }

    public class EmployeeMessages{
        public static final String SALARY_NOT_NEGATIVE = "Maaş 0'dan küçük olamaz.";
        public static final String EMPLOYEE_NOT_FOUND = " nolu id'ye sahip çalışan bulunmamaktadır.";
    }

    public class ModelMessages{
        public static final String MODEL_NAME_NOT_BLANK = "Model adı boş olamaz!";
        public static final String MODEL_NAME_LENGTH_MIN_TWO_LETTERS = "Girilen model adı en az 2 harfli olmalıdır.";
        public static final String MODEL_NOT_FOUND = " nolu id'ye sahip model bulunmamaktadır.";
        public static final String SAME_MODEL_NAME_EXISTS = "Bu model adı zaten var!!";

    }

    public class RentalMessages{
        public static final String RENTAL_NOT_FOUND = " nolu id'ye sahip rental bulunmamaktadır.";
        public static final String RENTAL_START_DATE_NOT_LESS_THAN_NOW_DATE = "başlangıç tarihi bugünden daha geçmiş bir tarih olamaz";
        public static final String RENTAL_END_DATE_NOT_LESS_THAN_START_DATE = "bitiş tarihi başlangıç tarihinden daha geçmiş bir tarih olamaz";
        public static final String RENTAL_TIME_MAX_TWENTY_FIVE_DAYS = "Bir araç maksimum 25 gün kiralanabilir.";
        public static final String RENTAL_TIME_MIN_ONE_DAY= "Bir araç minimum 1 gün kiralanabilir.";
        public static final String START_KILOMETER_NOT_LESS_THAN_END_KILOMETER = "Aracın son kilometresi,teslim alınan kilometreden düşük olamaz";
    }

    public class UserMessages{
        public static final String USER_NAME_NOT_BLANK = "İsim alanı boş geçilemez";
        public static final String USER_NAME_ONLY_LETTERS = "İsim alanı sadece harflerden oluşmalıdır.";
        public static final String USER_SURNAME_NOT_BLANK = "Soyisim alanı boş geçilemez";
        public static final String USER_SURNAME_ONLY_LETTERS = "Soyisim alanı sadece harflerden oluşmalıdır.";
        public static final String PHONE_NUMBER_NOT_START_ZERO = "Telefon numarasını başında sıfır olmadan giriniz!.";
        public static final String ENTER_VALID_EMAIL = "Geçerli bir e-posta adresi giriniz .";
        public static final String USER_NOT_FOUND = " nolu id'ye sahip user bulunmamaktadır.";
    }
    public class ImageMessages{
        public static final String BASE_PUBLIC_ID = "image/upload/";
        public static final String USE_FILENAME = "use_filename";
        public static final String UNIQUE_FILENAME = "unique_filename";
        public static final String OVERWRITE = "overwrite";
        public static final String URL = "url";
        public static final String FILE_UPLOADED_SUCCESSFULLY = "file uploaded successfully : ";
        public static final String FILE_UPLOADED_FAIL = "file uploaded Fail ";

    }
}
