package com.hemendra.auth_service.service;

import com.hemendra.auth_service.dto.LoginRequestDto;
import com.hemendra.auth_service.model.User;
import com.hemendra.auth_service.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    public boolean validateToken(String token) {
        try{
            jwtUtil.validateToken(token);
            return true;
        }
        catch(JwtException e){
            return false;
        }
    }

    public Optional<String> login(LoginRequestDto loginRequestDto) {
        Optional<String> token = userService.findByEmail(loginRequestDto.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDto.getPassword() ,
                u.getPassword())) //At login, you receive the plain-text password again. You can't reverse the hash, so passwordEncoder.matches() re-hashes the input and compares:
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }
}
