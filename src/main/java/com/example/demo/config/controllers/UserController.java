package com.example.demo.config.controllers;

import com.example.demo.config.models.Car;
import com.example.demo.config.models.User;
import com.example.demo.config.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201)) ;
    }
}
