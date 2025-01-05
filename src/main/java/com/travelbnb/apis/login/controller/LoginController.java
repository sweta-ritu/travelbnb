package com.travelbnb.apis.login.controller;

import com.travelbnb.apis.login.model.JWTTokenDto;
import com.travelbnb.apis.login.model.LoginDto;
import com.travelbnb.apis.login.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(LoginDto loginDto){
        String token = loginService.verifyLogin(loginDto);
        if(token != null) {
            JWTTokenDto jwtTokenDto = new JWTTokenDto();
            jwtTokenDto.setType("JWT_TOKEN");
            jwtTokenDto.setToken(token);
            return new ResponseEntity<>(jwtTokenDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unauthorized",HttpStatus.UNAUTHORIZED);
    }
}
