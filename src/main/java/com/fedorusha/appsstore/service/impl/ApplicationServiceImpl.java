package com.fedorusha.appsstore.service.impl;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.repository.ApplicationRepository;
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
    @Override
    public List<ApplicationDto> getAllApps() {
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        List<Apps> apps = applicationRepository.findAll();
        for (Apps app : apps) {
            ApplicationDto appDto = AppMapper.INSTANCE.toDTO(app);
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
    public Apps save(ApplicationDto applicationDto) {
        final Apps app = AppMapper.INSTANCE.fromDTO(applicationDto);
        log.info("Created application {}", app);
        return applicationRepository.save(app);
    }

    @Override
    public Apps update(ApplicationDto applicationDto, String appsName) {
        Apps app = applicationRepository.findByName(appsName);
        Apps changeApp = AppMapper.INSTANCE.fromDTO(applicationDto);
        app.setName(changeApp.getName());
        app.setDescription(changeApp.getDescription());
        log.info("Update application {}", app);
        return applicationRepository.save(app);
    }

    @Override
    @Transactional
    public void delete(String appsName) {
        Apps app = applicationRepository.findByName(appsName);
        applicationRepository.deleteById(app.getId());
        log.info("Delete completed {}", app);
    }
}
