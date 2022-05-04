package com.example.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String userType;

}
