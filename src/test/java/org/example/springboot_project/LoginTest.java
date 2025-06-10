package org.example.springboot_project;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    void shouldDeleteUserWithValidToken() throws Exception {
        // Skapa en användare först
        UserRegistrationDTO dto = new UserRegistrationDTO();
        dto.setUsername("testing2");
        dto.setPassword("Pasword1");
        dto.setConsentGiven(true);
        dto.setRole("ADMIN");

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

        // Hämta id på användaren (från databasen direkt om du vill)

        userRepository.findByUsername("testing2");

        Long idToDelete = 3L; // Byt mot riktigt ID


        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        "user", "password"
                ));

        String token = service.generateToken(auth);

        System.out.println("Token: " + token);


        mockMvc.perform(delete("/api/users/" + idToDelete)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

}

