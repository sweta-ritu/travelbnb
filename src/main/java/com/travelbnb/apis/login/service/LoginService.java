package com.travelbnb.apis.login.service;

import com.travelbnb.apis.login.model.LoginDto;
import com.travelbnb.apis.register.model.AppUser;
import com.travelbnb.apis.register.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService{
    private AppUserRepository appUserRepository;
    private JWTservice jwtService;


    public LoginService(AppUserRepository appUserRepository, JWTservice jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }


    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUserName = appUserRepository.findByUsername(loginDto.getUsername());
        if(opUserName.isPresent()){
            AppUser appUser = opUserName.get();
            if(BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())){
                return jwtService.generateToken(appUser);
            }
        }
        return null;
    }
}
