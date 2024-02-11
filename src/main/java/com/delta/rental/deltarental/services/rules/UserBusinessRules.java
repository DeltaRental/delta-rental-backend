package com.delta.rental.deltarental.services.rules;

import com.delta.rental.deltarental.entities.concretes.User;
import com.delta.rental.deltarental.repositories.UserRepository;
import com.delta.rental.deltarental.services.constants.Messages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private final UserRepository userRepository;

    //DB içerisinde User id' ye sahip user olup olmama durumu kontrolü
    public User checkByUserId(int id){
        if(!(userRepository.existsById(id))){
            throw new RuntimeException(id + Messages.UserMessages.USER_NOT_FOUND);
        }
        return userRepository.findById(id).orElseThrow();
    }




}
