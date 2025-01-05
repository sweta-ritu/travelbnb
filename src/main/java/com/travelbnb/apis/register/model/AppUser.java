package com.travelbnb.apis.register.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 100)
    @JsonIgnore // this will ignore the value during serialisation and deserialisation
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    //add email regex for validation
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // this will remove the value from response
    //store only encrypted password
    @Column(name = "password", nullable = false)
    private String password;

    //use enums for role instead of string
    public enum Role {
        ADMIN, HOTEL , USER
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role; // Use enums for better type safety and readability.
}