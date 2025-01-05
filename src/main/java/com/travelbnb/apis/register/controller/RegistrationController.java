package com.travelbnb.apis.register.controller;

import com.travelbnb.apis.register.model.AppUser;
import com.travelbnb.apis.register.repository.AppUserRepository;
import com.travelbnb.apis.register.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class RegistrationController {

    private final AppUserRepository appUserRepository;
    private final RegistrationService registrationService;

    public RegistrationController(AppUserRepository appUserRepository, RegistrationService registrationService) {
        this.appUserRepository = appUserRepository;
        this.registrationService = registrationService;
    }
    @Operation(
            security = @SecurityRequirement(name="basicAuth")
    )
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody AppUser user){
        //validate if username and email exists
        if(appUserRepository.existsByEmail(user.getEmail()) || appUserRepository.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("username or email exists", HttpStatus.BAD_REQUEST);
        }
        AppUser createdUser = registrationService.createUser(user);
        return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
    }
}
