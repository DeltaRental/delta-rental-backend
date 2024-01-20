package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.AuthService;
import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<GetAuthenticationResponse> register(
            @RequestBody AddUserRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<GetAuthenticationResponse> authenticate(
            @RequestBody AddAuthenticationRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
