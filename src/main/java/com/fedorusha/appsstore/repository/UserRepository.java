package com.fedorusha.appsstore.repository;

import com.fedorusha.appsstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
