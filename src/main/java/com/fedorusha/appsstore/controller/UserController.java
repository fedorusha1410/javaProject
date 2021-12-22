package com.fedorusha.appsstore.controller;

import com.fedorusha.appsstore.dto.*;
import com.fedorusha.appsstore.exceptions.UserException;
import com.fedorusha.appsstore.mapper.AppMapper;
import com.fedorusha.appsstore.mapper.UserMapper;
import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.User;
import com.fedorusha.appsstore.model.UsersApplication;
import com.fedorusha.appsstore.repository.UserRepository;
import com.fedorusha.appsstore.service.ApplicationService;
import com.fedorusha.appsstore.service.UserApplicationService;
import com.fedorusha.appsstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="User REST Controller", description="The controller accepts requests from the user page")
public class UserController {

    private final ApplicationService applicationService;
    private final UserApplicationService userApplicationService;
    private final UserService userService;
    private final UserRepository userRepository;



    @Operation(
            summary = "Getting all application from database",
            description = "Getting a list of all available applications"
    )
    @GetMapping("/apps")
    public ResponseEntity<List<ApplicationDto>> getApps() throws UserException {
        return ResponseEntity.ok(applicationService.getAllApps());
    }


    @Operation(
            summary = "Getting all users application from database",
            description = "Getting a list of all users applications"
    )
    @GetMapping("/usersApps/{id}")
    public ResponseEntity<?> getUsersApps(@PathVariable(value = "id") Long id, @RequestParam(value="page") int page) throws UserException {
        List<UsersApplication> list = userApplicationService.getAllUsersAppsByUserId(id, page);



        if (list != null) {
            List<ResponseUserAppDto> responseUserAppDtoList= new ArrayList<>();

            for(int i=0; i< list.size(); i++){
                ResponseUserAppDto tempUserApp= new ResponseUserAppDto();
                tempUserApp.setName(list.get(i).getApplication().getName());
                tempUserApp.setDesc(list.get(i).getApplication().getDescription());
                tempUserApp.setId_app(list.get(i).getApplication().getId());
                tempUserApp.setId_userApp(list.get(i).getId());
                responseUserAppDtoList.add(tempUserApp);

            }
            return new ResponseEntity<>(responseUserAppDtoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDto("Not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(
            summary = "Adding an application",
            description = "Adding an object to the list of custom applications"
    )
    @PostMapping("/add")
    public ResponseEntity addUserApp(@RequestBody @Valid RequestUsersAppDto requestUsersAppDto) throws UserException {

//        UsersAppDto usersAppDto= new UsersAppDto();
//        usersAppDto.setApp(applicationService.getByName(requestUsersAppDto.getApp_name()));

//        //usersAppDto.setUser();
//        usersAppDto.setName(requestUsersAppDto.getApp_name());

         userApplicationService.save(requestUsersAppDto);
       return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }

    @Operation(
            summary = "Removing an object from the list",
            description = "Removing an object from the list of custom applications"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUsersApp(@PathVariable(value = "id") Long id) throws UserException {
        Long delete_id=id;
        userApplicationService.deleteUsersApp(delete_id);
        return new ResponseEntity<>(new ResponseDto(), HttpStatus.OK);
    }


}