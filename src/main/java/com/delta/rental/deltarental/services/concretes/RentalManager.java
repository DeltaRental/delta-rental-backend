package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.Car;
import com.delta.rental.deltarental.entities.Rental;
import com.delta.rental.deltarental.repositories.RentalRepository;
import com.delta.rental.deltarental.services.abstracts.CarService;
import com.delta.rental.deltarental.services.abstracts.CustomerService;
import com.delta.rental.deltarental.services.abstracts.EmployeeService;
import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.dtos.requests.rental.AddRentalRequest;
import com.delta.rental.deltarental.services.dtos.requests.rental.UpdateRentalRequest;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalListResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
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

    @Override
    public GetRentalResponse getById(int id) {

        Rental rental = rentalRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(id + " nolu id' ye sahip bir rental id bulunmamaktadır.");
        });
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
        if(addRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException("başlangıç tarihi bugünden daha geçmiş bir tarih olamaz");
        }
        if (addRentalRequest.getEndDate().isBefore(addRentalRequest.getStartDate())){
            throw new RuntimeException("bitiş tarihi başlangıç tarihinden daha geçmiş bir tarih olamaz");
        }

        //Ekleme yaparken car id' nin db' de var olup olamama durumu kontrolü.
        carService.getById(addRentalRequest.getCarId());

        //Ekleme yaparken customer id' nin db' de var olup olamama durumu kontrolü
        //Customer da bir user olduğundan dolayı Customer id sine sahip bir veri bulamadığında user da yok demektir
        customerService.getById(addRentalRequest.getCustomerId());

        //Ekleme yaparken employee id' nin db' de var olup olamama durumu kontrolü
        //Employee da bir user olduğundan dolayı Employee id sine sahip bir veri bulamadığında user da yok demektir
        employeeService.getById(addRentalRequest.getEmployeeId());



        // Başlangıç ve bitiş tarihleri arasındaki gün sayısını kontrol et
        long rentalDays = addRentalRequest.getStartDate().until(addRentalRequest.getEndDate(), ChronoUnit.DAYS);

        // Eğer kiralama süresi 25 günden fazlaysa hata fırlat
        if (rentalDays > 25) {
            throw new IllegalArgumentException("Bir araç maksimum 25 gün kiralanabilir.");
        }

        //Model Mapping
        Rental rental = this.modelMapperService.forRequest()
                .map(addRentalRequest, Rental.class);


        //girilen car ıd nin verilerini set edebilmek için oluşturulan rental değişken
        GetCarResponse carId = carService.getById(addRentalRequest.getCarId());
        rental.setStartKilometer(carId.getKilometer());
        rental.setEndKilometer(null);

        //kiralamayı gün sayısına bağlı olarak hesaplanması ve toplam fiyata eklenmesi
        rental.setTotalPrice(carId.getDailyPrice() * rentalDays);

        rentalRepository.save(rental);


    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {
        if(updateRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException("başlangıç tarihi bugünden daha geçmiş bir tarih olamaz");
        }

        if (updateRentalRequest.getEndDate().isBefore(updateRentalRequest.getStartDate())){
            throw new RuntimeException("bitiş tarihi başlangıç tarihinden daha geçmiş bir tarih olamaz");
        }


        //Güncelleme yaparken car id' nin db' de var olup olamama durumu kontrolü.
        carService.getById(updateRentalRequest.getCarId());

        //Güncelleme yaparken customer id' nin db' de var olup olamama durumu kontrolü
        //Customer da bir user olduğundan dolayı Customer id sine sahip bir veri bulamadığında user da yok demektir
        customerService.getById(updateRentalRequest.getCustomerId());

        //Güncelleme yaparken employee id' nin db' de var olup olamama durumu kontrolü
        //Employee da bir user olduğundan dolayı Employee id sine sahip bir veri bulamadığında user da yok demektir
        employeeService.getById(updateRentalRequest.getEmployeeId());

        // Başlangıç ve bitiş tarihleri arasındaki gün sayısını kontrol et
        long rentalDays = updateRentalRequest.getStartDate().until(updateRentalRequest.getEndDate(), ChronoUnit.DAYS);

        // Eğer kiralama süresi 25 günden fazlaysa hata fırlat
        if (rentalDays > 25) {
            throw new IllegalArgumentException("Bir araç maksimum 25 gün kiralanabilir.");
        }



        //Model Mapping
        Rental rental = this.modelMapperService.forRequest()
                .map(updateRentalRequest, Rental.class);

        //girilen car ıd nin verilerini set edebilmek için oluşturulan rental değişken
        GetCarResponse getCarResponse = carService.getById(updateRentalRequest.getCarId());
        rental.setStartKilometer(getCarResponse.getKilometer());
        rental.setEndKilometer(null);

        //kiralamayı gün sayısına bağlı olarak hesaplanması ve toplam fiyata eklenmesi
        rental.setTotalPrice(getCarResponse.getDailyPrice() * rentalDays);

        //güncelleme yapılırken son kilometre, aracı teslim aldığı kilometreden daha az bir kilometrede olamaz.
        if(getCarResponse.getKilometer() > updateRentalRequest.getEndKilometer()){
            throw new RuntimeException("Aracın son kilometresi,teslim alınan kilometreden düşük olamaz");
        }

        rentalRepository.save(rental);
    }

    @Override
    public void delete(int id) {
        rentalRepository.deleteById(id);

    }
}
