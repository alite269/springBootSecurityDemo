package com.alite.springBootSecurityDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alite.springBootSecurityDemo.converter.AppUserConverter;
import com.alite.springBootSecurityDemo.dto.AppUserRequest;
import com.alite.springBootSecurityDemo.dto.AppUserResponse;
import com.alite.springBootSecurityDemo.entity.User;
import com.alite.springBootSecurityDemo.exception.NotFoundException;
import com.alite.springBootSecurityDemo.exception.UnprocessableEntityException;
import com.alite.springBootSecurityDemo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public AppUserResponse createUser(AppUserRequest request) {
        Optional<User> existingUser = repository.findByEmailAddress(request.getEmailAddress());
        if (existingUser.isPresent()) {
            throw new UnprocessableEntityException("This email address has been used.");
        }

        User user = AppUserConverter.toAppUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user = repository.insert(user);
        user = repository.save(user);
        return AppUserConverter.toAppUserResponse(user);
    }

    public AppUserResponse getUserResponseById(String id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find user."));

        return AppUserConverter.toAppUserResponse(user);
    }

    public User getUserByEmail(String email) {
        return repository.findByEmailAddress(email)
                .orElseThrow(() -> new NotFoundException("Can't find user."));
    }

    public List<AppUserResponse> getUserResponses() {
        List<User> users = repository.findAll();
        return AppUserConverter.toAppUserResponses(users);
    }
//    @PreAuthorize("hasRole('ADMIN') OR hasRole('FINANCE') OR hasRole('SUPERVISOR')") //  OR hasRole('USER')
    public User add(String name){
        User user = new User();
        user.setName(name);
        return repository.save(user);
    }
    
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Iterable<User> list(){
        Iterable<User> all = repository.findAll();
        return all;
    }
}