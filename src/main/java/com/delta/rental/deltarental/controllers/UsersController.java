package com.delta.rental.deltarental.controllers;

import com.delta.rental.deltarental.services.abstracts.AuthService;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.requests.user.UpdateUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserListResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
@CrossOrigin
public class UsersController {
    private final UserService userService;
    private final AuthService authService;


    @GetMapping("{id}")
    public GetUserResponse getById(int id){
        return userService.getById(id);
    }

    @GetMapping("/getAll")
    public List<GetUserListResponse> getAll(){
        return userService.getAll();
    }

    @PutMapping()
    public void update(@RequestBody UpdateUserRequest updateUserRequest){
        userService.update(updateUserRequest);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        userService.delete(id);
    }

    @GetMapping("/getUserInfo")
    public UserDetails getByEmail(String email){
        return userService.getByEmail(email);
    }
}






