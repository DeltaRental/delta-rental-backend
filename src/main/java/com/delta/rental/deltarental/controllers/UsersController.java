package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UsersController {
    private final UserService userService;
    @GetMapping("{id}")
    public GetUserResponse getById(int id){
        return userService.getById(id);
    }
}
