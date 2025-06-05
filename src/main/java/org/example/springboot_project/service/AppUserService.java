package org.example.springboot_project.service;

import jakarta.transaction.Transactional;
import org.example.springboot_project.web.LoggingComponent;
import org.example.springboot_project.exceptions.UserNotFoundException;
import org.example.springboot_project.model.AppUser;
import org.example.springboot_project.web.AppUserRegistrationDTO;
import org.example.springboot_project.web.AppUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    private final AppUserRepository repository;
    private final LoggingComponent logger;

    public AppUserService(AppUserRepository repository, LoggingComponent logger) {
        this.repository = repository;
        this.logger = logger;
    }
    @Transactional
    public AppUser registerNewUser(AppUserRegistrationDTO dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setConsentGiven(dto.isConsentGiven());
        user.setRole(dto.getRole());

        AppUser saved = repository.save(user);
        logger.log("Registered new user: " + saved.getUsername());
        return saved;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        repository.deleteById(id);
        logger.log("Deleted user with id: " + id);
    }

}
