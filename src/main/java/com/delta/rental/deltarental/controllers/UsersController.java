package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.RentalService;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;
import com.delta.rental.deltarental.services.dtos.responses.rental.GetRentalResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UsersController {
    private final UserService userService;


    @GetMapping("{id}")
    public GetUserResponse getById(int id){
        return userService.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<GetAuthenticationResponse> register(
            @RequestBody AddUserRequest request
    ){
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GetAuthenticationResponse> authenticate(
            @RequestBody AddAuthenticationRequest request
    ){
        return ResponseEntity.ok(userService.authenticate(request));
    }
}
