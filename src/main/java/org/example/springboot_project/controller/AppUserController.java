package org.example.springboot_project.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.example.springboot_project.model.UserRegistrationDTO;
import org.example.springboot_project.model.AppUser;
import org.example.springboot_project.service.AppUserService;
import org.springframework.http.ResponseEntity;

/**
 * den här klassen är en REST-controller som hanterar registrering och borttagning av användare
 * med hjälp av AppUserService, samt returnerar http-statuskoder
 */

@RestController
@RequestMapping("/api")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    /**
     * @param dto
     * @return ResponseEntity
     * registrerar ny användare med hjälp av data från UserRegistrationDTO
     *      och returnerar http-anrop 200 OK och nya användaren vid lyckad registrering
     */

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        AppUser newUser = userService.registerNewUser(dto);
        return ResponseEntity.ok(newUser);
    }

    /**
     * @param id
     * @return ResponseEntity
     *  kan endast anropas av admin
     *     tar bort användare utifrån id, returnerar ett tomt http-anrop vid lyckad borttagning
     */

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

