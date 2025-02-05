package com.dipartimento.projecthotelnew.dto;

public class LoginResponse {
    private String message;  // Messaggio di successo o errore
    private String username; // Username dell'utente
    private String role;     // Ruolo dell'utente
    private Integer userId;
    // Costruttore per inizializzare la risposta con il messaggio e i dati dell'utente
    public LoginResponse(String message, String username, String role, Integer userId) {
        this.message = message;
        this.username = username;
        this.role = role;
        this.userId=userId;
    }

    // Getter per il messaggio
    public String getMessage() {
        return message;
    }

    // Getter per l'username
    public String getUsername() {
        return username;
    }

    // Getter per il ruolo
    public String getRole() {
        return role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}