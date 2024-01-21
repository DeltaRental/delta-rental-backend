package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.UpdateUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;

public interface AuthService {

    GetAuthenticationResponse updateUserInformation(UpdateUserRequest request);
    GetAuthenticationResponse register(AddUserRequest request);
    GetAuthenticationResponse authenticate(AddAuthenticationRequest request);
}
