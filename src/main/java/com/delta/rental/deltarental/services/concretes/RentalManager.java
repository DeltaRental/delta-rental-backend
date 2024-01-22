package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Rental;
import com.delta.rental.deltarental.repositories.RentalRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.AddRentalRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.UpdateRentalRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalListResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import com.delta.rental.deltarental.services.rules.RentalBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final RentalBusinessRules rentalBusinessRules;

    @Override
    public GetRentalResponse getById(int id) {

        Rental rental = rentalBusinessRules.checkByRentalId(id);
        GetRentalResponse rentalResponse = modelMapperService.forResponse().map(rental, GetRentalResponse.class);
        return rentalResponse;
    }

    @Override
    public List<GetRentalListResponse> getAll() {
        List<Rental> rentalList = rentalRepository.findAll();

        List<GetRentalListResponse> rentalResponse = rentalList.stream()
                .map(rental ->this.modelMapperService.forResponse()
                        .map(rental, GetRentalListResponse.class)).collect(Collectors.toList());

        return rentalResponse;

    }

    @Override
    public void add(AddRentalRequest addRentalRequest) {
        long rentalDays = addRentalRequest.getStartDate().until(addRentalRequest.getEndDate(), ChronoUnit.DAYS);

        rentalBusinessRules.checkByStartDateIsBeforeCurrentDate(addRentalRequest.getStartDate());
        rentalBusinessRules.checkByEndDateIsBeforeStartDate(addRentalRequest.getEndDate(),addRentalRequest.getStartDate());
        rentalBusinessRules.checkByCarId(addRentalRequest.getCarId());
        rentalBusinessRules.checkByCustomerId(addRentalRequest.getCustomerId());
        rentalBusinessRules.checkByEmployeeId(addRentalRequest.getEmployeeId());
        rentalBusinessRules.checkRentalDays(rentalDays);

        //Model Mapping
        Rental rental = this.modelMapperService.forRequest()
                .map(addRentalRequest, Rental.class);


        //girilen car ıd nin verilerini set edebilmek için oluşturulan rental değişken
        GetCarResponse carId = carService.getById(addRentalRequest.getCarId());
        rental.setStartKilometer(carId.getKilometer());
        rental.setEndKilometer(null);
        //this.carService.update(this.modelMapperService.forRequest().map(carId,UpdateCarRequest.class));
        //UpdateCarRequest updateCarRequest = carId.setStatus(false);
        //carService.update(carId.setStatus());

        //kiralamayı gün sayısına bağlı olarak hesaplanması ve toplam fiyata eklenmesi
        rental.setTotalPrice(carId.getDailyPrice() * rentalDays);

        rentalRepository.save(rental);

        //*
        //GetCarResponse carResponse = carService.getById(addRentalRequest.getCarId());


    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {
        long rentalDays = updateRentalRequest.getStartDate().until(updateRentalRequest.getEndDate(), ChronoUnit.DAYS);

        rentalBusinessRules.checkByStartDateIsBeforeCurrentDate(updateRentalRequest.getStartDate());
        rentalBusinessRules.checkByEndDateIsBeforeStartDate(updateRentalRequest.getEndDate(),updateRentalRequest.getStartDate());
        rentalBusinessRules.checkByCarId(updateRentalRequest.getCarId());
        rentalBusinessRules.checkByCustomerId(updateRentalRequest.getCustomerId());
        rentalBusinessRules.checkByEmployeeId(updateRentalRequest.getEmployeeId());
        rentalBusinessRules.checkRentalDays(rentalDays);


        //Model Mapping
        Rental rental = this.modelMapperService.forRequest()
                .map(updateRentalRequest, Rental.class);

        //girilen car ıd nin verilerini set edebilmek için oluşturulan rental değişken
        GetCarResponse getCarResponse = carService.getById(updateRentalRequest.getCarId());
        rental.setStartKilometer(getCarResponse.getKilometer());

        //Arabanın kilometresini endKilometer ile güncelleyen kod.
        carService.updateCarKilometerWithEndKilometer(updateRentalRequest.getCarId(), rental.getEndKilometer());

        //kiralamayı gün sayısına bağlı olarak hesaplanması ve toplam fiyata eklenmesi
        double totalPrice = rentalBusinessRules.calculateTotalPrice(rentalDays,getCarResponse.getDailyPrice(), updateRentalRequest.getDiscount());
        rental.setTotalPrice(totalPrice);

        rentalBusinessRules.checkEndKilometerLessThanStartKilometer(getCarResponse.getKilometer(), updateRentalRequest.getEndKilometer());

        rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        rentalBusinessRules.checkByRentalId(id);
        rentalRepository.deleteById(id);
    }

    @Override
    public void carReturn(int carId,UpdateRentalRequest updateRentalRequest,AddRentalRequest addRentalRequest) {
        GetCarResponse carResponse = carService.getById(carId);
        if(updateRentalRequest.getReturnDate().isBefore(LocalDate.now())){
            carResponse.setStatus(false);
        }else {
            carResponse.setStatus(true);
        }

        this.carService.update(this.modelMapperService.forRequest().map(carResponse,UpdateCarRequest.class));

    }


}
