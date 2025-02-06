package com.dipartimento.projecthotelnew.dto;

public class LoginResponse {
    private String message;
    private String username;
    private String role;
    private Integer userId;

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