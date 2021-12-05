package com.fedorusha.appsstore.repository;

import com.fedorusha.appsstore.model.Apps;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Apps, Long> {
    Apps findByName(String appsName);

}
