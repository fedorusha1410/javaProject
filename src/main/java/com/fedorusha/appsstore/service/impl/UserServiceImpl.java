package com.fedorusha.appsstore.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fedorusha.appsstore.dto.UserDto;
import com.fedorusha.appsstore.mapper.UserMapper;
import com.fedorusha.appsstore.model.Role;
import com.fedorusha.appsstore.model.StatusUser;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.repository.RoleRepository;
import com.fedorusha.appsstore.repository.UserRepository;
import com.fedorusha.appsstore.service.MailSender;
import com.fedorusha.appsstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MailSender mailSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    private static final String ROLE_USER = "ROLE_USER";

    @Override
    public User findByUsername(String username) {
        log.info("UserService : findByUsername");
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(UserDto userDto) {
        User user = UserMapper.INSTANCE.fromDTO(userDto);
        User existUser = userRepository.findByUsername(user.getUsername());
        if (existUser == user) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "User is exist");
        }

        Role roleUser = roleRepository.findByName(ROLE_USER);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(StatusUser.ACTIVE);
        user.setCreated(new Date());
        user.setUpdated(new Date());

        sendMessageRegistration(user);

        log.info("Register - user: {} should activate account", user);
        return userRepository.save(user);
    }



    private void sendMessageRegistration(User user) {
        String message = String.format(
                "Hello, %s! \n" +
                        "You are registered successfully",
                user.getUsername());

        mailSender.send(user.getEmail(), "Registration", message);
    }




}
