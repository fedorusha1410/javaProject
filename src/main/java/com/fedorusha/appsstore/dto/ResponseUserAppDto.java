package com.fedorusha.appsstore.dto;


import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserAppDto {

    private String name;

    private String desc;

    private  Long id_userApp;

    private Long id_app;


}
