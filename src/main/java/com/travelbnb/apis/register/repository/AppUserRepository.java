package com.travelbnb.apis.register.repository;

import com.travelbnb.apis.register.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String Username);

    Optional<AppUser> findByUsername(String username);
}
