package com.alite.springBootSecurityDemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.alite.springBootSecurityDemo.entity.User;

public  interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmailAddress(String email);
    
    List<User> findAll();

}
