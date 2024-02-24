package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Rental;
import com.delta.rental.deltarental.repositories.RentalRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.constants.Messages;
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
    private final UserService userService;

    //DB içerisinde rental id' ye göre rental olup olmama durumu kontrolü
    public Rental checkByRentalId(int id){
        if(!(rentalRepository.existsById(id))){
            throw new RuntimeException(id + Messages.RentalMessages.RENTAL_NOT_FOUND);
        }
        return rentalRepository.findById(id).orElseThrow();
    }

    //Başlangıç tarihi bugünden daha geçmiş bir tarih olma durumu kontrolü
    public void checkByStartDateIsBeforeCurrentDate(LocalDate startDate){
        if(startDate.isBefore(LocalDate.now())){
            throw new RuntimeException(Messages.RentalMessages.RENTAL_START_DATE_NOT_LESS_THAN_NOW_DATE);
        }
    }

    //Bitiş tarihi başlangıç tarihinden daha geçmiş bir tarih olma durumu kontrolü
    public void checkByEndDateIsBeforeStartDate(LocalDate endDate, LocalDate startDate){
        if (endDate.isBefore(startDate)){
            throw new RuntimeException(Messages.RentalMessages.RENTAL_END_DATE_NOT_LESS_THAN_START_DATE);
        }
    }

    //Car id' nin db' de var olup olamama durumu kontrolü.
    public void checkByCarId(int id){
        carService.getById(id);
    }

    //Customer id' nin db' de var olup olamama durumu kontrolü.
    //Customer da bir user olduğundan dolayı Customer id sine sahip bir veri bulamadığında user da yok demektir
    public void checkByUserId(int id){
        userService.getById(id);
    }

    //Employee id' nin db' de var olup olamama durumu kontrolü.
    //Employee da bir user olduğundan dolayı Employee id sine sahip bir veri bulamadığında user da yok demektir
    public void checkByEmployeeId(int id){
        employeeService.getById(id);
    }


    // Eğer kiralama süresi 25 günden fazlaysa veya 1 günden azsa hata fırlat
    public void checkRentalDays(long rentalDays){
        if (rentalDays > 25) {
            throw new IllegalArgumentException(Messages.RentalMessages.RENTAL_TIME_MAX_TWENTY_FIVE_DAYS);
        } else if (rentalDays < 1) {
            throw new IllegalArgumentException(Messages.RentalMessages.RENTAL_TIME_MIN_ONE_DAY);
        }
    }

    //güncelleme yapılırken son kilometre, aracı teslim aldığı kilometreden daha az bir kilometrede olamaz.
    public void checkEndKilometerLessThanStartKilometer(Double startKilometer, Double endKilometer){
        if(startKilometer > endKilometer){
            throw new RuntimeException(Messages.RentalMessages.START_KILOMETER_NOT_LESS_THAN_END_KILOMETER);
        }
    }

    //kiralamayı gün sayısına bağlı olarak hesaplanması ve toplam fiyata eklenmesi
    public double calculateTotalPrice(long rentalDays, double dailyPrice, double discount){
        return (dailyPrice * rentalDays)-((dailyPrice * rentalDays)*(discount)/100);
    }

}
