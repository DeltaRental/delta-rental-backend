package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.Customer;
import com.delta.rental.deltarental.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerBusinessRules {
    private final CustomerRepository customerRepository;

    //DB içerisinde aynı Customer id' ye sahip customer olup olmama durumu kontrolü
    public Customer checkByCustomerId(int id){
        if(!(customerRepository.existsById(id))){
            throw new RuntimeException(id+" nolu id'ye sahip müşteri bulunmamaktadır.");
        }
        return customerRepository.findById(id).orElseThrow();
    }

    public void checkByNationalityId(String nationalityId){
        if((customerRepository.existsByNationalityId(nationalityId))){
            throw new RuntimeException(nationalityId + " bu kimlik numarası sistemde mevcuttur.");
        }

    }
}
