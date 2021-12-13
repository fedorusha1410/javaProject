package com.fedorusha.appsstore.service;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.model.User;

import java.util.List;


public interface UserService {
    User findByUsername(String username);

    User register(UserDto userDto);

    User findByEmail(String Email);

    List<UserDto> users();

    List<User> findAll();
}
