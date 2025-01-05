package com.travelbnb.apis.register.service;

import com.travelbnb.apis.register.model.AppUser;

public interface IRegistrationService {
    AppUser createUser(AppUser user);
}
