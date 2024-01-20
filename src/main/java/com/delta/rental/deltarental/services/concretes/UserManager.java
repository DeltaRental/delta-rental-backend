package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.User;
import com.delta.rental.deltarental.repositories.UserRepository;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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


}
