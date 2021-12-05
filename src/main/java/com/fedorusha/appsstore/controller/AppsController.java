package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AppsController {

    private final ApplicationService applicationService;

    @GetMapping("/apps")
    public ResponseEntity<List<ApplicationDto>> getApps() {
        return ResponseEntity.ok(applicationService.getAllApps());
    }

    @GetMapping("/apps/{appName}")
    public ResponseEntity<List<ApplicationDto>> searchApp(@PathVariable(name = "appName") String appName) {
        return ResponseEntity.ok(applicationService.searchApp(appName));
    }
    @PostMapping("/apps")
    public ResponseEntity<ApplicationDto> save(@Valid @RequestBody ApplicationDto applicationDto) {
        Apps app = applicationService.save(applicationDto);
        return ResponseEntity.ok(AppMapper.INSTANCE.toDTO(app));
    }

    @PutMapping("/apps/{appName}")
    public ResponseEntity<ApplicationDto> update(@Valid @RequestBody ApplicationDto applicationDto, @PathVariable(name = "appName") String appName) {
        Apps app = applicationService.update(applicationDto, appName);
        return ResponseEntity.ok(AppMapper.INSTANCE.toDTO(app));
    }

    @DeleteMapping("/apps/{appName}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(name = "appName") String appName) {
        applicationService.delete(appName);
    }

}
