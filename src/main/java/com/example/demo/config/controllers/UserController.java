package com.example.demo.config.controllers;

import com.example.demo.config.exception.OrderNotFoundException;
import com.example.demo.config.models.Car;
import com.example.demo.config.models.Order;
import com.example.demo.config.models.User;
import com.example.demo.config.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) throws Exception {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping(value = "/deleted/{id}")
    public ResponseEntity<String> deletedUserById(@PathVariable long id){
        return userService.deletedUserById(id);
    }

    @PutMapping(value = "/update")
    public void updateUser(@RequestBody User user) throws OrderNotFoundException {
        userService.updateUser(user);
    }
}
