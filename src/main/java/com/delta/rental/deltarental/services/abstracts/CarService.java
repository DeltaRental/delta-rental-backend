package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;

import java.util.List;

public interface CarService {
    void add (AddCarRequest addCarRequest);
    void update(UpdateCarRequest updateCarRequest, int id);
    void delete(int id);
    List<GetCarListResponse> getAll();
    GetCarResponse getById(int id);
}
