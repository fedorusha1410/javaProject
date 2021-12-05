package com.fedorusha.appsstore.service;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.model.User;



public interface UserService {
    User findByUsername(String username);

    User register(UserDto userDto);


    User forgotPassword(UserDto userDto);

    User resetPassword(String code, UserDto userDto);

}
