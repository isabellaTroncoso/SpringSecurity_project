package org.example.springboot_project.model;

import jakarta.persistence.*;

/**
 *  den här är en entity-klass vilket sparar information i databasen
 */

@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;

    private String password;

    private boolean consentGiven;

    private String role;

    public AppUser() {
    }

    public AppUser(String username, String password, boolean consentGiven, String role) {
        this.username = username;
        this.password = password;
        this.consentGiven = consentGiven;
        this.role = role;
    }

    public AppUser(Long id, String username, String password, boolean consentGiven, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.consentGiven = consentGiven;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
