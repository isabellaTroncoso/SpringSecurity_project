package org.example.springboot_project;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppLogin {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppLogin(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        //vi kollar först om denna användare redan finns
        if (appUserRepository.findByUsername("user") == null) {
            AppUser user = new AppUser();
            user.setUsername("user");
            //lösenordet hashas med passwordEncoders metod encode()
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole("ADMIN");
            appUserRepository.save(user);
        }
    }

}
