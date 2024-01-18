package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;
import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;


public interface UserService {
    GetUserResponse getById(int id);
    GetAuthenticationResponse register(AddUserRequest request);
    GetAuthenticationResponse authenticate(AddAuthenticationRequest request);
}
