package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.ApplicationDto;
import com.fedorusha.appsstore.exceptions.AdminException;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.service.ApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Admin REST Controller", description="The controller accepts requests from the admin page")
public class AppsController {

    private final ApplicationService applicationService;

    @Operation(
            summary = "List of all applications",
            description = "The administrator gets a list of all data"
    )
    @GetMapping("/apps")
    public ResponseEntity<List<ApplicationDto>> getApps() throws AdminException {
        return ResponseEntity.ok(applicationService.getAllApps());
    }

    @Operation(
            summary = "Search by name",
            description = "Method for finding an application by name"
    )
    @GetMapping("/apps/{appName}")
    public ResponseEntity<List<ApplicationDto>> searchApp(@PathVariable(name = "appName") String appName) throws AdminException {
        return ResponseEntity.ok(applicationService.searchApp(appName));
    }

    @Operation(
            summary = "Object conservation",
            description = "Method for saving the application from the database"
    )
    @PostMapping("/apps")
    public ResponseEntity<ApplicationDto> save(@Valid @RequestBody ApplicationDto applicationDto) throws AdminException {
        Apps app = applicationService.save(applicationDto);
        return ResponseEntity.ok(AppMapper.INSTANCE.toDTO(app));
    }

    @Operation(
            summary = "Updating an object",
            description = "Method for updating an application from the database"
    )
    @PutMapping("/apps/{appName}")
    public ResponseEntity<ApplicationDto> update(@Valid @RequestBody ApplicationDto applicationDto, @PathVariable(name = "appName") String appName) throws AdminException {
        Apps app = applicationService.update(applicationDto, appName);
        return ResponseEntity.ok(AppMapper.INSTANCE.toDTO(app));
    }

    @Operation(
            summary = "Deleting an object",
            description = "Method for removing an application from the database"
    )
    @DeleteMapping("/apps/{appName}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable(name = "appName") String appName) throws AdminException {
        applicationService.delete(appName);
    }

}
