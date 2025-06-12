package org.example.springboot_project.service;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.example.springboot_project.model.UserRegistrationDTO;
import org.example.springboot_project.component.LoggingComponent;
import org.example.springboot_project.exceptions.UserNotFoundException;
import org.example.springboot_project.model.AppUser;
import org.example.springboot_project.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

// det här är en service-klass som registrerar och tar bort användare
// samt initialiserar en admin-användare vid uppstart

@Service
public class AppUserService {
    @Autowired
    private final AppUserRepository repository;

    @Autowired
    private final LoggingComponent logger;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository repository,
                          LoggingComponent logger,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.logger = logger;
        this.passwordEncoder = passwordEncoder;
    }

    // den här metoden registrerar en ny användare och krypterar lösenordet samt sparar infon i loggen
    @Transactional
    public AppUser registerNewUser(UserRegistrationDTO dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Användarnamnet '" + dto.getUsername() + "' är redan upptaget.");
        }

        AppUser user = new AppUser();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setConsentGiven(dto.isConsentGiven());
        user.setRole(dto.getRole());

        AppUser saved = repository.save(user);
        logger.log("Registrerade ny användare: " + saved.getUsername());
        return saved;
    }

    // den här metoden tar bort en användare utifrån id:t samt sparar infon i loggen
    @Transactional
    public void deleteUser(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        repository.deleteById(id);
        logger.log("Tog bort användare med ID: " + id);
    }

    // initierar en admin-användare i databasen när app startas om den inte finns
    @PostConstruct
    public void init() {
        if (repository.findByUsername("user").isEmpty()) {
            AppUser user = new AppUser();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            repository.save(user);
        }
    }
}
