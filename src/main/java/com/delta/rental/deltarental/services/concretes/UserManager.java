package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.User;
import com.delta.rental.deltarental.repositories.UserRepository;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.requests.rental.UpdateRentalRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserListResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRules userBusinessRules;


    @Override
    public GetUserResponse getById(int id) {
        User user = userBusinessRules.checkByUserId(id);
        GetUserResponse userResponse = modelMapperService.forResponse().map(user, GetUserResponse.class);
        return userResponse;
    }

    @Override
    public List<GetUserListResponse> getAll() {
        List<User> userList = userRepository.findAll();

        List<GetUserListResponse> userResponse = userList.stream()
                .map(user ->this.modelMapperService.forResponse()
                        .map(user, GetUserListResponse.class)).collect(Collectors.toList());
        return userResponse;

    }

    @Override
    public void add(AddUserRequest addUserRequest) {
        User user = this.modelMapperService.forRequest()
                .map(addUserRequest, User.class);

        user.setGsm(user.getGsm().trim().replaceAll("\\s", ""));
        user.setEmail(user.getEmail().trim().toLowerCase());

        userRepository.save(user);
    }

    @Override
    public void update(UpdateRentalRequest updateRentalRequest) {
        User user = this.modelMapperService.forRequest()
                .map(updateRentalRequest, User.class);

        user.setGsm(user.getGsm().trim().replaceAll("\\s", ""));
        user.setEmail(user.getEmail().trim().toLowerCase());

        userRepository.save(user);


    }

    @Override
    public void delete(int id) {

    }


}
