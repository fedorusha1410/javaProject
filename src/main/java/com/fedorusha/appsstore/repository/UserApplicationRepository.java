package com.fedorusha.appsstore.repository;

import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.UsersApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserApplicationRepository extends JpaRepository<UsersApplication, Long> {

    List<UsersApplication> getUsersApplicationsByUser_Id(Long id);

    List<UsersApplication> getUsersApplicationByApplication(Apps apps);


}

