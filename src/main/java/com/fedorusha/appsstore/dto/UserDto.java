package com.fedorusha.appsstore.dto;

import com.fedorusha.appsstore.validator.CellPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {


   // private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank

    private String password;
}
