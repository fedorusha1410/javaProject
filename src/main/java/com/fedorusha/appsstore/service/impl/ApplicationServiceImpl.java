package com.fedorusha.appsstore.service.impl;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.dto.InsertingAppDto;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.UsersApplication;
import com.fedorusha.appsstore.repository.ApplicationRepository;
import com.fedorusha.appsstore.repository.UserApplicationRepository;
import com.fedorusha.appsstore.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private  final ApplicationRepository applicationRepository;
    private final UserApplicationRepository userApplicationRepository;
    @Override
    public List<ApplicationDto> getAllApps() {
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        List<Apps> apps = applicationRepository.findAll();
        for (Apps app : apps) {
            ApplicationDto appDto = new ApplicationDto();
            appDto.setName(app.getName());
            appDto.setDesc(app.getDescription());
            appDto.setId(app.getId());
            applicationDtos.add(appDto);
        }
        return applicationDtos;
    }

    @Override
    public List<ApplicationDto> searchApp(String appsName) {
        List<Apps> apps = new ArrayList<>();
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        apps.add(applicationRepository.findByName(appsName));
        for (Apps app : apps) {
            ApplicationDto applicationDto = AppMapper.INSTANCE.toDTO(app);
            applicationDtos.add(applicationDto);
        }
        return applicationDtos;
    }


    @Override
    @Transactional
    public Apps save(InsertingAppDto insertingAppDto) {

        final Apps app = new Apps();
        app.setName(insertingAppDto.getName());
        app.setDescription(insertingAppDto.getDesc());
        log.info("Inserted new  application {}", app);
        return applicationRepository.save(app);
    }

    @Override
    public Apps update(ApplicationDto applicationDto) {
        Apps app = applicationRepository.findByName(applicationDto.getName());

        app.setDescription(applicationDto.getDesc());
        log.info("Update application {}", app);
        return applicationRepository.save(app);
    }

    @Override
    @Transactional
    public void delete(String appsName) {
        Apps app = applicationRepository.findByName(appsName);
        List<UsersApplication> temp = userApplicationRepository.getUsersApplicationByApplication(app);
        for(int i=0; i<temp.size(); i++){
            userApplicationRepository.delete(temp.get(i));
        }
        applicationRepository.deleteById(app.getId());
        log.info("Delete completed {}", app);
    }

    @Override
    public Apps getByName(String name_app) {
        Apps app= applicationRepository.findByName(name_app);
        log.info("Get app by name {}", app);
        return  app;
    }

    @Override
    public Apps getById(Long id){

        Apps app=applicationRepository.getById(id);
        log.info("Getting application by id {}", app);
        return  app;

    }


}
