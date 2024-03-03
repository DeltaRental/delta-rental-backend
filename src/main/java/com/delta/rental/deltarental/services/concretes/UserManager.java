package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.concretes.User;
import com.delta.rental.deltarental.repositories.UserRepository;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.constants.Messages;
import com.delta.rental.deltarental.services.dtos.requests.user.UpdateUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserListResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User getByEmail(String email) {


        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(Messages.GeneralMessages.WRONG_INFORMATION));
    }

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
    public void add(User addUserRequest) {

        User user = this.modelMapperService.forRequest()
                .map(addUserRequest, User.class);

        user.setGsm(user.getGsm().trim().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));
        user.setEmail(user.getEmail().trim().toLowerCase());

        userRepository.save(user);
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        User user = this.modelMapperService.forRequest()
                .map(updateUserRequest, User.class);

        user.setGsm(user.getGsm().trim().replaceAll(Messages.GeneralMessages.REPLACE_ALL_REGEX, Messages.GeneralMessages.REPLACE_ALL_REPLACEMENT));
        user.setEmail(user.getEmail().trim().toLowerCase());
        //Şifreyi güncellerken passwordEncoder ile kripto olarak güncelleme işlemi
        user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));

        userRepository.save(user);


    }

    @Override
    public void delete(int id) {
        userBusinessRules.checkByUserId(id);
        userRepository.deleteById(id);
    }


}
