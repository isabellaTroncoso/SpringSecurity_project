package org.example.springboot_project.web;

import org.example.springboot_project.repository.AppUserRepository;
import org.example.springboot_project.model.AppUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * den här klassen hämtar en användare från databasen utifrån användarnamn
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private AppUserRepository appUserRepository;

    public MyUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    /**
     * @param username
     * @return UserDetails
     * @throws UsernameNotFoundException
     *  den här metoden kollar om en användare finns i databasen
     *   och returnerar ett objekt som motsvarar användaren
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = appUserRepository.findByUsername(username);

        if (appUserOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        AppUser appUser = appUserOptional.get();

        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                true, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );
    }
}
