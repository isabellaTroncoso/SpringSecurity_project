package org.example.springboot_project;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springboot_project.model.AppUser;
import org.example.springboot_project.model.UserRegistrationDTO;
import org.example.springboot_project.repository.AppUserRepository;
import org.example.springboot_project.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private TokenService service;

    @Autowired
    private AuthenticationManager manager;
    private String token;

    @BeforeEach
    void setupUser() {

        userRepository.deleteAll();

        AppUser user = new AppUser();
        user.setUsername("user");
        user.setPassword(encoder.encode("password"));
        user.setRole("ADMIN");
        user.setConsentGiven(true);
        userRepository.save(user);
    }

    @Test
    void shouldLoginSuccessfullyAndReturnToken() throws Exception {
        String loginJson = """
        {
            "username": "user",
            "password": "password"
        }
    """;

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRegisterUser() throws Exception {
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("user2");
        dto.setPassword("Password1");
        dto.setConsentGiven(true);
        dto.setRole("ADMIN");

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteUserWithValidToken() throws Exception {
        // Hämta användaren som skapats i @BeforeEach
        Optional<AppUser> foundUser = userRepository.findByUsername("user");
        Long idToDelete = foundUser.get().getId();

        // Autentisera användaren och generera JWT-token
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken("user", "password")
        );
        String token = service.generateToken(auth);

        // Försök att ta bort användaren med korrekt token
        mockMvc.perform(delete("/api/users/" + idToDelete)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().is2xxSuccessful());
    }

}

