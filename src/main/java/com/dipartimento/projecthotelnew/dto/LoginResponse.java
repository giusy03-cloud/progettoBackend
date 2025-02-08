package com.dipartimento.projecthotelnew.dto;


//QUESTO DTO RAPPRESENTA LA RISPOSTA INVIATA DAL SERVER QUANDO UN UTENTE TENTA DI EFFETTUARE IL LOGIN
public class LoginResponse {
    private String message;  // Messaggio di successo o errore
    private String username; // Nome dell'utente che ha effettuato il login
    private String role;  // Ruolo dell'utente (ad esempio, "admin" o "user")
    private Integer userId; // ID dell'utente

    public LoginResponse(String message, String username, String role, Integer userId) {
        this.message = message;
        this.username = username;
        this.role = role;
        this.userId=userId;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }


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