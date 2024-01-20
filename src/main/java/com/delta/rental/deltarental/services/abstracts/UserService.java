package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.services.dtos.requests.rental.UpdateRentalRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserListResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;

import java.util.List;


public interface UserService {
    GetUserResponse getById(int id);
    List<GetUserListResponse> getAll();
    void add(AddUserRequest addUserRequest);
    void update(UpdateRentalRequest updateRentalRequest);
    void delete(int id);



}
