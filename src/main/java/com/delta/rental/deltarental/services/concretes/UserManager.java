package com.delta.rental.deltarental.services.concretes;

import com.delta.rental.deltarental.core.services.JwtService;
import com.delta.rental.deltarental.core.utilities.mappers.ModelMapperService;
import com.delta.rental.deltarental.entities.User;
import com.delta.rental.deltarental.enums.UserRole;
import com.delta.rental.deltarental.repositories.UserRepository;
import com.delta.rental.deltarental.services.abstracts.UserService;
import com.delta.rental.deltarental.services.dtos.requests.authentication.AddAuthenticationRequest;
import com.delta.rental.deltarental.services.dtos.requests.user.AddUserRequest;
import com.delta.rental.deltarental.services.dtos.responses.authentication.GetAuthenticationResponse;
import com.delta.rental.deltarental.services.dtos.responses.user.GetUserResponse;
import com.delta.rental.deltarental.services.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperService modelMapperService;
    private final UserBusinessRules userBusinessRules;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public GetUserResponse getById(int id) {
        User user = userBusinessRules.checkByUserId(id);
        GetUserResponse userResponse = modelMapperService.forResponse().map(user, GetUserResponse.class);
        return userResponse;
    }

    public GetAuthenticationResponse register(AddUserRequest request) {
        var user = User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .gsm(request.getGsm())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getRoles())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();
    }

    public GetAuthenticationResponse authenticate(AddAuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return GetAuthenticationResponse.builder().token(jwtToken).build();
    }
}
