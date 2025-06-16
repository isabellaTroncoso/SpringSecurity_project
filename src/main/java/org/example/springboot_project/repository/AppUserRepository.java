package org.example.springboot_project.repository;

import org.example.springboot_project.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * ett interface som extenderar JpaRepository och hämtar användare utifrån username
 */

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);


}
