package com.alite.springBootSecurityDemo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alite.springBootSecurityDemo.entity.User;
import com.alite.springBootSecurityDemo.exception.NotFoundException;
import com.alite.springBootSecurityDemo.service.UserService;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User appUser = userService.getUserByEmail(username);
            return new SpringUser(appUser);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Username is wrong.");
        }
    }

}
