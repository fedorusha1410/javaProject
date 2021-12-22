package com.fedorusha.appsstore.service;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.dto.InsertingAppDto;
import com.fedorusha.appsstore.model.Apps;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDto> getAllApps();

    List<ApplicationDto> searchApp(String appsName);

    Apps save(InsertingAppDto insertingAppDto);

    Apps getById(Long Id);

    Apps update(ApplicationDto applicationDto);

    void delete(String appsName);

    Apps getByName(String name_app);
}
