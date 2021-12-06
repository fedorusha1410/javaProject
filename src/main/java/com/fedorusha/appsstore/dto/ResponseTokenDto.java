package com.fedorusha.appsstore.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTokenDto {
    private String username;
    private Long id;
    private String role;
    private String token;
    private String error;
}
