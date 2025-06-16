package org.example.springboot_project.model;

/**
 * @param username
 * @param password
 * den här klassen är ett record som skapar ett oföränderligt objekt som används vid inloggning
 * den genererar automatiskt constructor, getters och setters
 */

public record LoginRequest(String username, String password) {

}
