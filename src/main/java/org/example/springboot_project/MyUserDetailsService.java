package org.example.springboot_project;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private AppUserRepository appUserRepository;

    public MyUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //hämta användaren från databasen via repositoryts metod
        AppUser appUser = appUserRepository.findByUsername(username);
        if(appUser == null){
        //om användaren ej hittas - kasta detta inbyggda exception
            throw new UsernameNotFoundException("User not found");
        }
        //returnera ett objekt av Springs klass UserDetails
        //en inbyggd klass i Spring som motsvarar en användare
        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                true, true, true, true,
        //viktigt: rollen måste prefixas med ROLE_ här
                List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );
    }
}
