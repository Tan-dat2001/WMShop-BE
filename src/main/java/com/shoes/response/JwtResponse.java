package com.shoes.response;


import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private String userId;

    public JwtResponse(String accessToken, String username, List<String> roles, String userId) {
        this.token = accessToken;
        this.username = username;
        this.roles = roles;
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
