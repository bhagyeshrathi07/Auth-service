package com.example.auth_service.service;

import com.example.auth_service.entity.User;
import com.example.auth_service.models.RegistrationRequest;
import com.example.auth_service.models.UserResponse;
import com.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired private UserRepository userRepository;

    public void register(RegistrationRequest request) {
        userRepository.save(new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword()));
    }

    public UserResponse getUser(String email){
        User person = userRepository.findByEmail(email);
        return new UserResponse(
                person.getUserId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail()
        );
    }
}
