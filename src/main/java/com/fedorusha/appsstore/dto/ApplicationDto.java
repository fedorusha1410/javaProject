package com.fedorusha.appsstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationDto {


    private  Long id;

    private String name;

    private String desc;
}
