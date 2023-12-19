package com.shoes.response;


import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;
    private String userId;
    private String firstName;
    private String lastName;

    public JwtResponse(String accessToken, String email, List<String> roles, String userId, String firstName, String lastName) {
        this.token = accessToken;
        this.email = email;
        this.roles = roles;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
