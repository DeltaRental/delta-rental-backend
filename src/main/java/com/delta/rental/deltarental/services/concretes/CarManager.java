package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.repositories.CarRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private ModelService modelService;
    private ColorService colorService;

    @Override
    public GetCarResponse getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse carResponse = modelMapperService.forResponse().map(car, GetCarResponse.class);
        return carResponse;
    }

    @Override
    public List<GetCarListResponse> getAll() {
        List<Car> carList = carRepository.findAll();

        List<GetCarListResponse> carResponse = carList.stream()
                .map(car ->this.modelMapperService.forResponse()
                        .map(car, GetCarListResponse.class)).collect(Collectors.toList());
        return carResponse;
    }


    @Override
    public void add(AddCarRequest addCarRequest) {
        if(carRepository.existsByPlate(addCarRequest.getPlate().trim().toUpperCase()))
        {
            throw new RuntimeException("Aynı plakada başka bir araç eklenemez.");
        }


        //Ekleme yaparken model id' nin db' de var olup olamama durumu kontrolü.
        if (modelService.getById(addCarRequest.getModelId()) == null){
            //throw new RuntimeException(addCarRequest.getModelId()+" nolu id' ye sahip model bulunmamaktadır.");
        }

        //Ekleme yaparken color id' nin db' de var olup olamama durumu kontrolü.
        if (colorService.getById(addCarRequest.getColorId()) == null){
            //throw new RuntimeException(addCarRequest.getColorId()+" nolu id' ye sahip renk bulunmamaktadır.");
        }

        Car car = this.modelMapperService.forRequest()
                .map(addCarRequest, Car.class);
        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {

        //Güncelleme yaparken model id' nin db' de var olup olamama durumu kontrolü.
        if (!(carRepository.existsByModelId(updateCarRequest.getModelId()))){
            throw new RuntimeException(updateCarRequest.getModelId()+" nolu id' ye sahip model bulunmamaktadır.");
        }

        //Güncelleme yaparken color id' nin db' de var olup olamama durumu kontrolü.
        if (!(carRepository.existsByColorId(updateCarRequest.getColorId()))){
            throw new RuntimeException(updateCarRequest.getColorId()+" nolu id' ye sahip renk bulunmamaktadır.");
        }

        if(carRepository.findById(updateCarRequest.getId()) != null){
            throw new RuntimeException(updateCarRequest.getId()+" nolu id'ye sahip araç bulunmamaktadır.");
        }

        Car car = this.modelMapperService.forRequest()
                .map(updateCarRequest, Car.class);
        carRepository.save(car);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }



}
