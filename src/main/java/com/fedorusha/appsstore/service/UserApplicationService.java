package com.fedorusha.appsstore.service;

import com.fedorusha.appsstore.model.UsersApplication;

import java.util.List;

public interface UserApplicationService {

    List<UsersApplication> getAllUsersAppsByUserId(Long id);

    void deleteUsersApp(Long id);


}
