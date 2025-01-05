package com.travelbnb.apis.register.service;

import com.travelbnb.apis.register.model.AppUser;
import com.travelbnb.apis.register.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements IRegistrationService{


    private final AppUserRepository appUserRepository;

    public RegistrationService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    /** creating new instance of BCryptPasswordEncoder and making it private to restrict the
     visibility of instance to RegistrationService class ensuring that no other class can access or
     modify this encoder directly,following the principle of encapsulation and also making it final
     to ensure that the reference to the BCryptPasswordEncoder object cannot be changed after being
     initialized which guarantees immutability for the encoder reference.
     **/
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public AppUser createUser(AppUser user) {
        //validate if user exists before saving it in database
        // hash the password before storing in the db
        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return appUserRepository.save(user);
    }

    public String hashPassword(String password){
        // default is 10 but using 12 to make hashing more secure
        int workFactor = 12;
        return BCrypt.hashpw(password,BCrypt.gensalt(workFactor));
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
