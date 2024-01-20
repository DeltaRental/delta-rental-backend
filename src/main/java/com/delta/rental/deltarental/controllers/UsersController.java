package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserListResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UsersController {
    private final UserService userService;


    @GetMapping("{id}")
    public GetUserResponse getById(int id){
        return userService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetUserListResponse> getAll(){
        return userService.getAll();
    }





}
