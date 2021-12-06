package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.*;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.mapper.UserMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.UsersApplication;
import com.fedorusha.appsstore.repository.UserRepository;
import com.fedorusha.appsstore.service.ApplicationService;
import com.fedorusha.appsstore.service.UserApplicationService;
import com.fedorusha.appsstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final ApplicationService applicationService;
    private final UserApplicationService userApplicationService;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/apps")
    public ResponseEntity<List<ApplicationDto>> getApps() {
        return ResponseEntity.ok(applicationService.getAllApps());
    }


    @GetMapping("/usersApps/{id}")
    public ResponseEntity<?> getUsersApps(@PathVariable(value = "id") Long id) throws Exception {
        List<UsersApplication> list = userApplicationService.getAllUsersAppsByUserId(id);


        if (list != null) {
            List<ResponseUserAppDto> responseUserAppDtoList= new ArrayList<>();

            for(int i=0; i< list.size(); i++){
                ResponseUserAppDto tempUserApp= new ResponseUserAppDto();
                tempUserApp.setName(list.get(i).getApplication().getName());
                tempUserApp.setId(list.get(i).getApplication().getId());
                responseUserAppDtoList.add(tempUserApp);
            }
            return new ResponseEntity<>(responseUserAppDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addUserApp(@RequestBody @Valid RequestUsersAppDto requestUsersAppDto) {

        UsersAppDto usersAppDto= new UsersAppDto();
        usersAppDto.setApp(applicationService.getByName(requestUsersAppDto.getApp_name()));
        usersAppDto.setUser(userRepository.getById(requestUsersAppDto.getId()));
        usersAppDto.setName(requestUsersAppDto.getApp_name());
         userApplicationService.save(usersAppDto);
       return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUsersApp(@PathVariable(value = "id") Long id) {
        userApplicationService.deleteUsersApp(id);
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }


}