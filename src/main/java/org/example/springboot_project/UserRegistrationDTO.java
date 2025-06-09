package org.example.springboot_project;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public @NotBlank(message = "Username måste anges") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username måste anges") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Lösenord måste anges") @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Lösenord måste innehålla minst 8 tecken, en stor bokstav och en siffra") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Lösenord måste anges") @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$", message = "Lösenord måste innehålla minst 8 tecken, en stor bokstav och en siffra") String password) {
        this.password = password;
    }

    @AssertTrue(message = "Samtycke måste ges")
    public boolean isConsentGiven() {
        return consentGiven;
    }

    public void setConsentGiven(@AssertTrue(message = "Samtycke måste ges") boolean consentGiven) {
        this.consentGiven = consentGiven;
    }
}

