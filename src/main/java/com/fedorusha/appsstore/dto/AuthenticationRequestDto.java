package com.fedorusha.appsstore.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private final String username;
    private final String password;
}
