package com.alite.springBootSecurityDemo.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alite.springBootSecurityDemo.entity.User;
import com.alite.springBootSecurityDemo.repository.UserRepository;
import com.alite.springBootSecurityDemo.service.UserService;

import io.swagger.annotations.Api;

//@RestController
@Controller
@RequestMapping("/user")
@Api(tags = "使用者")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    /**
     * 新增一条数据
     * @param name
     * @return
     */
    @PreAuthorize("hasRole('ADMIN') OR hasRole('FINANCE') OR hasRole('SUPERVISOR')") //  OR hasRole('USER')
    @PostMapping("/add")
    public User add(String name){
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }
    /**
     * 查询所有数据
     * @return
     */
//    @PreAuthorize("hasAuthority('User')")
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/list")
    public ResponseEntity<String> list(){
        Iterable<User> all = userRepository.findAll();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(all.toString(), headers, HttpStatus.OK);
    }

    @PostMapping("/add2")
    public User add2(String name){
        return userService.add(name);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/list2")
    public ResponseEntity<String> list2(){
        Iterable<User> all = userService.list();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<String>(all.toString(), headers, HttpStatus.OK);
//        return all;
    }
}