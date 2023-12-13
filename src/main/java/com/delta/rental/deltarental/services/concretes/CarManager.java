package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.entities.Color;
import com.delta.rental.deltarental.entities.Model;
import com.delta.rental.deltarental.repositories.CarRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();

        GetCarResponse dto = new GetCarResponse();
        dto.setKilometer(car.getKilometer());
        dto.setYear(car.getYear());
        dto.setDailyPrice(car.getDailyPrice());
        dto.setPlate(car.getPlate());
        dto.setModelId(car.getModel().getId());
        dto.setColorId(car.getColor().getId());

        return dto;
    }

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> carList = carRepository.findAll();
        List<GetCarListResponse> getCarListResponses = new ArrayList<>();

        for (Car car : carList) {
            GetCarListResponse dto = new GetCarListResponse();
            dto.setKilometer(car.getKilometer());
            dto.setYear(car.getYear());
            dto.setDailyPrice(car.getDailyPrice());
            dto.setPlate(car.getPlate());
            dto.setModelId(car.getModel().getId());
            dto.setColorId(car.getColor().getId());
            getCarListResponses.add(dto);
        }
        return getCarListResponses;
    }


    @Override
    public void add(AddCarRequest addCarRequest) {
        if(carRepository.existsByPlate(addCarRequest.getPlate().trim().toUpperCase()))
        {
            throw new RuntimeException("Aynı plakada başka bir araç eklenemez.");
        }

        Car car = new Car();
        car.setKilometer(addCarRequest.getKilometer());
        car.setYear(addCarRequest.getYear());
        car.setDailyPrice(addCarRequest.getDailyPrice());
        car.setPlate(addCarRequest.getPlate().toUpperCase());

        //Ekleme yaparken model id' nin db' de var olup olamama durumu kontrolü.
        if (carRepository.existsByModelId(addCarRequest.getModelId())){
            car.setModel(new Model(addCarRequest.getModelId()));
        }else {
            throw new RuntimeException(addCarRequest.getModelId()+" nolu id' ye sahip model bulunmamaktadır.");
        }

        //Ekleme yaparken color id' nin db' de var olup olamama durumu kontrolü.
        if (carRepository.existsByColorId(addCarRequest.getColorId())){
            car.setColor(new Color(addCarRequest.getColorId()));
        }else {
            throw new RuntimeException(addCarRequest.getColorId()+" nolu id' ye sahip renk bulunmamaktadır.");
        }

        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest, int id) {
        Car carToUpdate = carRepository.findById(id).orElseThrow();
        carToUpdate.setKilometer(updateCarRequest.getKilometer());
        carToUpdate.setYear(updateCarRequest.getYear());
        carToUpdate.setDailyPrice(updateCarRequest.getDailyPrice());
        carToUpdate.setPlate(updateCarRequest.getPlate());

        //Güncelleme yaparken model id' nin db' de var olup olamama durumu kontrolü.
        if (carRepository.existsByModelId(updateCarRequest.getModelId())){
            carToUpdate.setModel(new Model(updateCarRequest.getModelId()));
        }else{
            throw new RuntimeException(updateCarRequest.getModelId()+" nolu id' ye sahip model bulunmamaktadır.");
        }

        //Güncelleme yaparken color id' nin db' de var olup olamama durumu kontrolü.
        if (carRepository.existsByColorId(updateCarRequest.getColorId())){
            carToUpdate.setColor(new Color(updateCarRequest.getColorId()));
        }else {
            throw new RuntimeException(updateCarRequest.getColorId()+" nolu id' ye sahip renk bulunmamaktadır.");
        }

        carRepository.save(carToUpdate);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }



}
