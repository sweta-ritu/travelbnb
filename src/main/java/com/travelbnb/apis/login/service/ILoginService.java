package com.travelbnb.apis.login.service;

import com.travelbnb.apis.login.model.LoginDto;

public interface ILoginService {
    String verifyLogin(LoginDto loginDto);
}
