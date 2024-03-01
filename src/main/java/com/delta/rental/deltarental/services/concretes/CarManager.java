package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.Car;
import com.delta.rental.deltarental.entities.concretes.Rental;
import com.delta.rental.deltarental.repositories.CarRepository;
import com.delta.rental.deltarental.services.abstracts.BranchService;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.ImageService;
import com.delta.rental.deltarental.services.constants.Messages;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.CarFilterDto;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private ModelMapperService modelMapperService;
    private CarBusinessRules carBusinessRules;
    private ImageService imageService;
    private BranchService branchService;

    @Override
    public GetCarResponse getById(int id) {
        Car car = carBusinessRules.checkByCarId(id);
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
        carBusinessRules.checkByModelId(addCarRequest.getModelId());
        carBusinessRules.checkByColorId(addCarRequest.getColorId());
        carBusinessRules.checkByPlate(addCarRequest.getPlate());


        //Model Mapper işlemi
        Car car = this.modelMapperService.forRequest()
                .map(addCarRequest, Car.class);

        car.setPlate(car.getPlate().trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));
        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {
        carBusinessRules.checkByCarId(updateCarRequest.getId());
        carBusinessRules.checkByModelId(updateCarRequest.getModelId());
        carBusinessRules.checkByColorId(updateCarRequest.getColorId());
        carBusinessRules.checkByPlateWhenUpdate(updateCarRequest.getId(), updateCarRequest.getPlate());

        //Model Mapper işlemi
        Car car = this.modelMapperService.forRequest()
                .map(updateCarRequest, Car.class);

        carRepository.save(car);
    }

    @Override
    public void delete(int id) {
        carBusinessRules.checkByCarId(id);
        carRepository.deleteById(id);
    }

    @Override
    public void updateCarKilometerWithEndKilometer(int id, Double endKilometer) {
        Car car = carBusinessRules.checkByCarId(id);
        car.setKilometer(endKilometer);
        carRepository.save(car);
    }

    @Override
    public void updateCarLocationWithReturnLocation(int id, String returnLocation) {
        Car car = carBusinessRules.checkByCarId(id);
        car.setLocation(returnLocation);
        carRepository.save(car);

    }


    public List<GetCarListResponse> filterCars(CarFilterDto carFilterDto) {
        List<Car> cars = carRepository.filterCars(carFilterDto.getStartLocation(), carFilterDto.getEndDate(), carFilterDto.getStartDate());
        List<GetCarListResponse> availableCars = cars.stream()
                .map(car ->this.modelMapperService.forResponse()
                        .map(car, GetCarListResponse.class)).collect(Collectors.toList());
        return availableCars;
    }

    @Override
    public List<GetCarListResponse> getSearchPlate(String plate) {
        List<Car> cars = carRepository.searchPlate(plate);
        List<GetCarListResponse> getPlate = cars.stream()
                .map(car ->this.modelMapperService.forResponse()
                        .map(car, GetCarListResponse.class)).collect(Collectors.toList());
        return getPlate;
    }

}
