package com.cdac.ridesharingapplication.dto;

import com.cdac.ridesharingapplication.model.User;

public class AuthResponse {
    private String message;
    private boolean success;
    private String token;
    private User user;
    
    // Default constructor
    public AuthResponse() {
    }
    
    // Constructor with parameters
    public AuthResponse(String message, boolean success, String token, User user) {
        this.message = message;
        this.success = success;
        this.token = token;
        this.user = user;
    }
    
    // Getters and Setters
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
}
