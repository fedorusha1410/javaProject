package com.fedorusha.appsstore.dto;


import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestUsersAppDto {
    private Long id;
    private String app_name;
    private String username;

}
