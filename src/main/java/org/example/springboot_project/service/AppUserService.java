package org.example.springboot_project.service;

import jakarta.transaction.Transactional;
import org.example.springboot_project.UserRegistrationDTO;
import org.example.springboot_project.web.LoggingComponent;
import org.example.springboot_project.exceptions.UserNotFoundException;
import org.example.springboot_project.model.AppUser;
import org.example.springboot_project.web.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class AppUserService {
    private final AppUserRepository repository;
    private final LoggingComponent logger;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository repository,
                          LoggingComponent logger,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.logger = logger;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser registerNewUser(UserRegistrationDTO dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Användarnamnet '" + dto.getUsername() + "' är redan upptaget.");
        }

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 🔐 Kryptera lösenordet
        user.setConsentGiven(dto.isConsentGiven());
        user.setRole(dto.getRole());

        AppUser saved = repository.save(user);
        logger.log("Registrerade ny användare: " + saved.getUsername());
        return saved;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        repository.deleteById(id);
        logger.log("Tog bort användare med ID: " + id);
    }
}
