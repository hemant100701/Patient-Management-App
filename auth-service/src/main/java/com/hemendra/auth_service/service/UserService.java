package com.hemendra.auth_service.service;

import com.hemendra.auth_service.Repository.AuthRepo;
import com.hemendra.auth_service.dto.LoginRequestDto;
import com.hemendra.auth_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    AuthRepo authRepo;

    public Optional<User> findByEmail(String email) {
        return authRepo.findByEmail(email);
    }
}
