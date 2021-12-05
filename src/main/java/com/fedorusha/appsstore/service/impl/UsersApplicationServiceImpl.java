package com.fedorusha.appsstore.service.impl;

import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.UsersApplication;
import com.fedorusha.appsstore.repository.UserApplicationRepository;
import com.fedorusha.appsstore.service.UserApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersApplicationServiceImpl implements UserApplicationService {

    private  final UserApplicationRepository userApplicationRepository;
    @Override
    public List<UsersApplication> getAllUsersAppsByUserId(Long id) {
        return userApplicationRepository.getUsersApplicationsByUser_Id(id);
    }


    @Override
    @Transactional
    public void deleteUsersApp(Long id) {
        userApplicationRepository.deleteById(id);
        log.info("Delete completed {}", userApplicationRepository.getById(id).getApplication().getName());
    }
}
