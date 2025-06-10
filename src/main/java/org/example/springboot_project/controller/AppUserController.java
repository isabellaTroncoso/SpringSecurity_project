package org.example.springboot_project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.example.springboot_project.model.UserRegistrationDTO;
import org.example.springboot_project.model.AppUser;
import org.example.springboot_project.service.AppUserService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        AppUser newUser = userService.registerNewUser(dto);
        return ResponseEntity.ok(newUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

