package org.example.springboot_project.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.AssertTrue;

public class UserRegistrationDTO {

    @NotBlank(message = "Username måste anges")
    private String username;

    @NotBlank(message = "Lösenord måste anges")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Lösenord måste innehålla minst 8 tecken, en stor bokstav och en siffra")
    private String password;

    @AssertTrue(message = "Samtycke måste ges")
    private boolean consentGiven;

    @NotBlank(message = "Roll måste anges")
    private String role;

    public UserRegistrationDTO() {}


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(boolean consentGiven) {
        this.consentGiven = consentGiven;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

