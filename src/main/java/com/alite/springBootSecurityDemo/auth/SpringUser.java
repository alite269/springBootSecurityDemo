package com.alite.springBootSecurityDemo.auth;

import com.alite.springBootSecurityDemo.entity.User;

import lombok.Value;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SpringUser implements UserDetails {
    private User appUser;

    public SpringUser(User appUser) {
        this.appUser = appUser;
    }

    public String getId() {
        return appUser.getId();
    }

    public String getName() {
        return appUser.getName();
    }

    public String getRole() {
        return appUser.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return appUser.getAuthorities().stream()
//                .map(auth -> new SimpleGrantedAuthority(auth.name()))
//                .collect(Collectors.toList());
    	List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    	list.add(new SimpleGrantedAuthority(appUser.getAuthorities()));
//        list.add(new SimpleGrantedAuthority(appUser.getAuthorities().name()));

        return list;
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
