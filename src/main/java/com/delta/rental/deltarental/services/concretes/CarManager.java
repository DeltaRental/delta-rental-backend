package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.repositories.CarRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;

    @Override
    public void add(AddCarRequest addCarRequest) {
        if(carRepository.existsByPlate(addCarRequest.getPlate().trim()))
        {
            throw new RuntimeException("Aynı plakada başka bir araç eklenemez.");
        }

        Car car = new Car();
        car.setKilometer(addCarRequest.getKilometer());
        car.setYear(addCarRequest.getYear());
        car.setDailyPrice(addCarRequest.getDailyPrice());
        car.setPlate(addCarRequest.getPlate());

        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest, int id) {
        Car carToUpdate = carRepository.findById(id).orElseThrow();
        carToUpdate.setKilometer(updateCarRequest.getKilometer());
        carToUpdate.setYear(updateCarRequest.getYear());
        carToUpdate.setDailyPrice(updateCarRequest.getDailyPrice());
        carToUpdate.setPlate(updateCarRequest.getPlate());

        carRepository.save(carToUpdate);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public List<GetCarListResponse> getAll() {
        return null;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();

        GetCarResponse getCarResponse = new GetCarResponse();

        return getCarResponse;
    }
}
