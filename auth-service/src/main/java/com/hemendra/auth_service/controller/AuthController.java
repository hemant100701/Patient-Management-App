package com.hemendra.auth_service.controller;

import com.hemendra.auth_service.dto.LoginRequestDto;
import com.hemendra.auth_service.dto.LoginResponseDto;
import com.hemendra.auth_service.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;
    @Operation(summary = "Generate token on login")
    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        Optional<String> optionaltoken = authService.login(loginRequestDto);

        if(optionaltoken.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto(optionaltoken.get());
        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @Operation(summary = "Validate the token")
    @GetMapping("validate")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authHeader){
        if(authHeader==null || !authHeader.startsWith("Bearer "))
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return (authService.validateToken(authHeader.substring(7))) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
