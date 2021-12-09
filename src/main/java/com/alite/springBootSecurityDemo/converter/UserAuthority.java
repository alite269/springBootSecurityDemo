package com.alite.springBootSecurityDemo.converter;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum UserAuthority {
    ADMIN, USER;

    @JsonCreator
    public UserAuthority fromString(String key) {
    	UserAuthority userAuthority = Arrays.stream(values())
        .filter(value -> value.name().equalsIgnoreCase(key))
        .findFirst()
        .orElse(null);
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(key))
                .findFirst()
                .orElse(null);
    }
}
