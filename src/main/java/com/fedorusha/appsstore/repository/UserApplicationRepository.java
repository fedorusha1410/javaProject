package com.fedorusha.appsstore.repository;

import com.fedorusha.appsstore.model.Apps;
import com.fedorusha.appsstore.model.UsersApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserApplicationRepository extends JpaRepository<UsersApplication, Long> {

    Page<UsersApplication> getUsersApplicationByUser_Id(Long id, Pageable pageable);

    List<UsersApplication> getUsersApplicationsByUser_Id(Long id);

    List<UsersApplication> getUsersApplicationByApplication(Apps apps);

    void deleteUsersApplicationByApplication(Long id);

}

