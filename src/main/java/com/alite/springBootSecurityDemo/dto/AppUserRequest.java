package com.alite.springBootSecurityDemo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

import java.util.List;

@Data
public class AppUserRequest {
    @NotBlank
    private String emailAddress;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotEmpty
    private String role;
//    private List<UserAuthority> authorities;

//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    public void setEmailAddress(String emailAddress) {
//        this.emailAddress = emailAddress;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

//    public List<UserAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public void setAuthorities(List<UserAuthority> authorities) {
//        this.authorities = authorities;
//    }
}
