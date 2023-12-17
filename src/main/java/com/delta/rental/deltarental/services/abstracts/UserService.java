package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.responses.car.GetCarResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;


public interface UserService {
    GetUserResponse getById(int id);
}
