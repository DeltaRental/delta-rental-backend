package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Car;
import com.delta.rental.deltarental.repositories.CarRepository;
import com.delta.rental.deltarental.services.abstracts.ColorService;
import com.delta.rental.deltarental.services.abstracts.ModelService;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarBusinessRules {
    private final CarRepository carRepository;
    private final ModelService modelService;
    private final ColorService colorService;

    //Model id' nin db' de var olup olamama durumu kontrolü.
    public void checkByModelId(int id){
        modelService.getById(id);
    }

    //Color id' nin db' de var olup olamama durumu kontrolü.
    public void checkByColorId(int id){
        colorService.getById(id);
    }

    //DB içerisinde aynı plakaya sahip araçların var olup olmama kontrolü
    public void checkByPlate(String plate){
        if(carRepository.existsByPlate(plate.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT)))
        {
            throw new RuntimeException(Messages.CarMessages.SAME_PLATE_CAR_EXISTS);
        }
    }

    //DB içerisinde aynı Car id' ye sahip araç olup olmama durumu kontrolü
    public Car checkByCarId(int id){
        if(!(carRepository.existsById(id))){
            throw new RuntimeException(id + Messages.CarMessages.CAR_NOT_FOUND);
        }
        return carRepository.findById(id).orElseThrow();
    }

    public void checkByPlateWhenUpdate(int id, String plate){
        Optional<Car> existingCarOptional = carRepository.findById(id);
        Car existingCar = existingCarOptional.get();
        String newPlate = plate.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT);

        //Eğer DB de girilen plakaya sahip başka bir plaka var ise bu hata oluşur.Ancak yok ise güncellenir(kendi plakasıda dahil).

        if (!existingCar.getPlate().equals(newPlate) && carRepository.existsByPlate(newPlate)) {
            throw new RuntimeException(Messages.CarMessages.SAME_PLATE_CAR_EXISTS);
        }

    }

    public String byPlate(String plate){
        String licensePlate = plate.trim().toUpperCase().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT);
        boolean exists = this.carRepository.existsByPlate(licensePlate);
        if(exists){
            throw new RuntimeException(plate + Messages.CarMessages.SAME_PLATE_CAR_EXISTS);
        }
        return licensePlate;
    }
}
