package org.example.springboot_project.exceptions;

// den här klassen är ett exception som kastas om användaren med id inte hittas
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found: " + id);
    }
}
