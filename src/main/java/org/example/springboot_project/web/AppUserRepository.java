package org.example.springboot_project.web;

import org.example.springboot_project.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    boolean existsByUsername(String username);


}
