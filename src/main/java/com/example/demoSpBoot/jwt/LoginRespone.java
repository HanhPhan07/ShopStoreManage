package com.example.demoSpBoot.jwt;

import java.util.Optional;

import com.example.demoSpBoot.model.users;

import lombok.Data;

@Data
public class LoginRespone {
    private String accessToken;
    private String tokenType;
    private CustomUserDetails user;
    
    public LoginRespone(String accessToken,CustomUserDetails user) {
    	this.setTokenType("Bearer");
        this.setAccessToken(accessToken);
        this.user = user;
    }
    
    public String getAccessToken() {
    	return this.accessToken;
    }
    public void setAccessToken(String accessToken) {
    	this.accessToken = accessToken;
    }
    public String getTokenType() {
    	return this.tokenType;
    }
    public void setTokenType(String tokenType) {
    	this.tokenType = tokenType;
    }

	public CustomUserDetails getUser() {
		return user;
	}

	public void setUser(CustomUserDetails user) {
		this.user = user;
	}
}
