package com.alite.springBootSecurityDemo.dto;

import java.util.List;

import lombok.Data;

@Data
public class AppUserResponse {
    private String id;
    private String emailAddress;
    private String name;
    private String role;
}
