package com.alite.springBootSecurityDemo.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.alite.springBootSecurityDemo.dto.AppUserRequest;
import com.alite.springBootSecurityDemo.dto.AppUserResponse;
import com.alite.springBootSecurityDemo.entity.User;

public class AppUserConverter {

    private AppUserConverter() {

    }

    public static User toAppUser(AppUserRequest request) {
        User user = new User();
        user.setEmailAddress(request.getEmailAddress());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setRole(request.getRole());

        return user;
    }

    public static AppUserResponse toAppUserResponse(User user) {
        AppUserResponse response = new AppUserResponse();
        response.setId(user.getId());
        response.setEmailAddress(user.getEmailAddress());
        response.setName(user.getName());
        response.setRole(user.getRole());

        return response;
    }

    public static List<AppUserResponse> toAppUserResponses(List<User> users) {
        return users.stream()
                .map(AppUserConverter::toAppUserResponse)
                .collect(Collectors.toList());
    }
}
