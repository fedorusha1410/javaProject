package com.fedorusha.appsstore.service;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.dto.RequestUsersAppDto;
import com.fedorusha.appsstore.dto.UsersAppDto;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.UsersApplication;

import java.util.List;

public interface UserApplicationService {

    List<UsersApplication> getAllUsersAppsByUserId(Long id);

    void deleteUsersApp(Long id);

    void deleteByApp(Long id);

    UsersApplication save(RequestUsersAppDto requestUsersAppDto);

}
