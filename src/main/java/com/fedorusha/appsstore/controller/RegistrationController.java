package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.mapper.UserMapper;
import com.fedorusha.appsstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/registration")
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@Valid @RequestBody UserDto user) {
        UserMapper.INSTANCE.toDTO(userService.register(user));
    }
}
