package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.User;
import com.delta.rental.deltarental.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    //DB içerisinde aynı User id' ye sahip user olup olmama durumu kontrolü
    public User checkByUserId(int id){
        if(!(userRepository.existsById(id))){
            throw new RuntimeException(id+" nolu id'ye sahip user bulunmamaktadır.");
        }
        return userRepository.findById(id).orElseThrow();
    }
}
