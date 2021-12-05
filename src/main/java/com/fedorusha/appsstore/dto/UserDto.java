package com.fedorusha.appsstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserDto {

    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
