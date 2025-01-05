package com.travelbnb.apis.login.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTTokenDto {
    public String type;
    public String token;
}
