package com.travelbnb.apis.login.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.travelbnb.apis.register.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTservice {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiry.duration}")
    private long expiryTime;

    private Algorithm algorithm;

    @Autowired
    private void initAlgorithm() {
        if (algorithm == null) {
            algorithm = Algorithm.HMAC256(algorithmKey);
        }
    }


    public String generateToken(AppUser appUser){
        String USER_NAME = "username";
        return JWT.create()
                .withClaim(USER_NAME, appUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
