package com.fedorusha.appsstore.dto;

import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersAppDto {

    String name;

    Apps app;

    User user;

}
