package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.exceptions.RegistrationException;
import com.fedorusha.appsstore.mapper.UserMapper;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/registration")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Registration REST Controller", description="The controller accepts requests from the registration page")
public class RegistrationController {

    private final UserService userService;

    @Operation(
            summary = "User's registration",
            description = "Allows you to register a user"
    )
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@Valid @RequestBody UserDto user) throws RegistrationException {
       UserDto newUser= new UserDto();
       newUser=user;
        userService.register(newUser);
    }
}
