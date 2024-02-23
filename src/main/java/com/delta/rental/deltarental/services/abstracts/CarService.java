package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.CarFilterDto;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CarService {
    GetCarResponse getById(int id);
    List<GetCarListResponse> getAll();
    void add (AddCarRequest addCarRequest);
    void update(UpdateCarRequest updateCarRequest);
    void delete(int id);
    void updateCarKilometerWithEndKilometer(int id,Double endKilometer);
    void updateCarLocationWithReturnLocation(int id,String returnLocation);
    List<GetCarListResponse> filterCars(CarFilterDto carFilterDto);

    List<GetCarListResponse> getSearchPlate(String plate);
}
