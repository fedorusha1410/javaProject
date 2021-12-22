package com.fedorusha.appsstore.service.impl;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.dto.RequestUsersAppDto;
import com.fedorusha.appsstore.dto.UsersAppDto;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.model.UsersApplication;
import com.fedorusha.appsstore.repository.ApplicationRepository;
import com.fedorusha.appsstore.repository.UserApplicationRepository;
import com.fedorusha.appsstore.repository.UserRepository;
import com.fedorusha.appsstore.service.UserApplicationService;
import com.fedorusha.appsstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersApplicationServiceImpl implements UserApplicationService {

    private final UserApplicationRepository userApplicationRepository;
    private final UserService userService;
    private final ApplicationRepository applicationRepository;


    @Override
    public List<UsersApplication> getAllUsersAppsByUserId(Long id) {

        return userApplicationRepository.getUsersApplicationsByUser_Id(id);
    }


    @Override
    @Transactional
    public void deleteUsersApp(Long id) {
        UsersApplication newUserApp= userApplicationRepository.getById(id);
        userApplicationRepository.delete(newUserApp);
        //userApplicationRepository.deleteById(id);
        log.info("Delete completed {}", userApplicationRepository.getById(id).getApplication().getName());
    }

    @Override
    @Transactional
    public void deleteByApp(Long id) {

        userApplicationRepository.deleteUsersApplicationByApplication(id);
    }


    @Override
    @Transactional
    public UsersApplication save(RequestUsersAppDto requestUsersAppDto) {
        if (requestUsersAppDto != null) {

            UsersApplication Users_app = new UsersApplication();
            String username = requestUsersAppDto.getUsername();
            User user = userService.findByUsername(username);

            Apps apps = applicationRepository.findByName(requestUsersAppDto.getApp_name());
            if (user != null && apps != null) {
                Users_app.setApplication(apps);
                Users_app.setUser(user);
                log.info("Save user application {}", Users_app);
                return userApplicationRepository.save(Users_app);
            } else {
                return null;
            }


        } else {
            return null;
        }

    }
}
