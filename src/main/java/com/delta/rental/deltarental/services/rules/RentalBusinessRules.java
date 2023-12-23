package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.Rental;
import com.delta.rental.deltarental.repositories.RentalRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RentalBusinessRules {
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;

    //DB içerisinde aynı rental id' ye sahip rental olup olmama durumu kontrolü
    public Rental checkByRentalId(int id){
        if(!(rentalRepository.existsById(id))){
            throw new RuntimeException(id+" nolu id'ye sahip rental bulunmamaktadır.");
        }
        return rentalRepository.findById(id).orElseThrow();
    }

    //Başlangıç tarihi bugünden daha geçmiş bir tarih olma durumu kontrolü
    public void checkByStartDateIsBeforeCurrentDate(LocalDate startDate){
        if(startDate.isBefore(LocalDate.now())){
            throw new RuntimeException("başlangıç tarihi bugünden daha geçmiş bir tarih olamaz");
        }
    }

    //Bitiş tarihi başlangıç tarihinden daha geçmiş bir tarih olma durumu kontrolü
    public void checkByEndDateIsBeforeStartDate(LocalDate endDate, LocalDate startDate){
        if (endDate.isBefore(startDate)){
            throw new RuntimeException("bitiş tarihi başlangıç tarihinden daha geçmiş bir tarih olamaz");
        }
    }

    //Car id' nin db' de var olup olamama durumu kontrolü.
    public void checkByCarId(int id){
        carService.getById(id);
    }

    //Customer id' nin db' de var olup olamama durumu kontrolü.
    //Customer da bir user olduğundan dolayı Customer id sine sahip bir veri bulamadığında user da yok demektir
    public void checkByCustomerId(int id){
        customerService.getById(id);
    }

    //Employee id' nin db' de var olup olamama durumu kontrolü.
    //Employee da bir user olduğundan dolayı Employee id sine sahip bir veri bulamadığında user da yok demektir
    public void checkByEmployeeId(int id){
        employeeService.getById(id);
    }


    // Eğer kiralama süresi 25 günden fazlaysa veya 1 günden azsa hata fırlat
    public void checkRentalDays(long rentalDays){
        if (rentalDays > 25) {
            throw new IllegalArgumentException("Bir araç maksimum 25 gün kiralanabilir.");
        } else if (rentalDays < 1) {
            throw new IllegalArgumentException("Bir araç minimum 1 gün kiralanabilir.");
        }
    }

    //güncelleme yapılırken son kilometre, aracı teslim aldığı kilometreden daha az bir kilometrede olamaz.
    public void checkEndKilometerLessThanStartKilometer(Double startKilometer, Double endKilometer){
        if(startKilometer > endKilometer){
            throw new RuntimeException("Aracın son kilometresi,teslim alınan kilometreden düşük olamaz");
        }
    }

    //kiralamayı gün sayısına bağlı olarak hesaplanması ve toplam fiyata eklenmesi
    public double calculateTotalPrice(long rentalDays, double dailyPrice, double discount){
        return (dailyPrice * rentalDays)-((dailyPrice * rentalDays)*(discount)/100);
    }

}
