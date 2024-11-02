package com.example.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthUser {
    String userName;
    String email;
    String password;

}
