package com.delta.rental.deltarental.services.abstracts;

import com.delta.rental.deltarental.entities.concretes.User;
import com.delta.rental.deltarental.services.dtos.requests.user.UpdateUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserListResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {

    User getByEmail(String email);
    GetUserResponse getById(int id);
    List<GetUserListResponse> getAll();
    void add(User addUserRequest);
    void update(UpdateUserRequest updateUserRequest);
    void delete(int id);



}
