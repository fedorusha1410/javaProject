package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.AuthenticationRequestDto;
import com.fedorusha.appsstore.dto.ResponseTokenDto;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.security.jwt.JwtTokenProvider;
import com.fedorusha.appsstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationRequestDto requestDto) throws AuthenticationException {
        final String username = requestDto.getUsername();
        final String password = requestDto.getPassword();
        ResponseTokenDto tokenResponseDto = new ResponseTokenDto();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        String token = jwtTokenProvider.createToken(username, user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        tokenResponseDto.setToken(token);
        tokenResponseDto.setId(user.getId());
        tokenResponseDto.setRole(user.getRoles().toString());
        tokenResponseDto.setUsername(username);
        response.put("token", token);

        log.info("Get request : /api/auth/login");
        return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
    }

}
