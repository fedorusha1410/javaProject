package com.fedorusha.appsstore.service;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.model.Apps;

import java.util.List;

public interface ApplicationService {
    List<ApplicationDto> getAllApps();

    List<ApplicationDto> searchApp(String appsName);

    Apps save(ApplicationDto applicationDto);

    Apps getById(Long Id);

    Apps update(ApplicationDto applicationDto,String appsName);

    void delete(String appsName);

    Apps getByName(String name_app);
}
