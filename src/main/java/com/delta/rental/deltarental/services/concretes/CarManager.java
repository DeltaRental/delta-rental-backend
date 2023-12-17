package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.entities.Rental;
import com.delta.rental.deltarental.repositories.CarRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.dtos.requests.car.AddCarRequest;
import com.delta.rental.deltarental.services.dtos.requests.car.UpdateCarRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarListResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.color.GetColorResponse;
import com.delta.rental.deltarental.services.dtos.responses.model.GetModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

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
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip bir car id bulunmamaktadır.");
        });
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

        //Ekleme yaparken model id' nin db' de var olup olamama durumu kontrolü.
        modelService.getById(addCarRequest.getModelId());


        //Ekleme yaparken color id' nin db' de var olup olamama durumu kontrolü.
        colorService.getById(addCarRequest.getColorId());

        //Plaka eklerken DB içerisinde aynı plakaya sahip araçların var olup olmama kontrolü
        if(carRepository.existsByPlate(addCarRequest.getPlate().trim().toUpperCase().replaceAll("\\s", "")))
        {
            throw new RuntimeException("Aynı plakada başka bir araç eklenemez.");
        }

        //Model Mapper işlemi
        Car car = this.modelMapperService.forRequest()
                .map(addCarRequest, Car.class);

        car.setPlate(car.getPlate().trim().toUpperCase().replaceAll("\\s", ""));

        carRepository.save(car);
    }

    @Override
    public void update(UpdateCarRequest updateCarRequest) {

        if(!(carRepository.existsById(updateCarRequest.getId()))){
            throw new RuntimeException(updateCarRequest.getId()+" nolu id'ye sahip araç bulunmamaktadır.");
        }

        //Güncelleme yaparken model id' nin db' de var olup olamama durumu kontrolü.
        modelService.getById(updateCarRequest.getModelId());


        //Güncelleme yaparken color id' nin db' de var olup olamama durumu kontrolü.
        colorService.getById(updateCarRequest.getColorId());



        //Kullanıcının güncellemek istediği aracın plakasını , DB 'de aynı plakaya sahip başka bir araç var mı durumunun kontrolünü sağlayan kod
        Optional<Car> existingCarOptional = carRepository.findById(updateCarRequest.getId());
        Car existingCar = existingCarOptional.get();
        String newPlate = updateCarRequest.getPlate().trim().toUpperCase().replaceAll("\s", "");

        //Eğer DB de girilen plakaya sahip başka bir plaka var ise bu hata oluşur.Ancak yok ise güncellenir(kendi plakasıda dahil).

        if (!existingCar.getPlate().equals(newPlate) && carRepository.existsByPlate(newPlate)) {
            throw new RuntimeException("Bu plakaya sahip zaten bir araç var !!");
        }


        //Model Mapper işlemi
        Car car = this.modelMapperService.forRequest()
                .map(updateCarRequest, Car.class);


        carRepository.save(car);
    }

    @Override
    public void delete(int id) {
        carRepository.deleteById(id);
    }



}
