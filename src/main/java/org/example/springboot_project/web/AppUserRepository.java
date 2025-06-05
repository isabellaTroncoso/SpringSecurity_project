package org.example.springboot_project.web;

import org.example.springboot_project.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
    AppUser addUser(AppUser user);
    AppUser deleteUser(long id);
    boolean existsByUsername(String username);

}
