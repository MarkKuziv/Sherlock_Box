package com.sherlock.box.controllers;

import com.sherlock.box.dto.UserDTO;
import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.exception.OrderNotFoundException;
import com.sherlock.box.exception.UserNotFoundException;
import com.sherlock.box.models.User;
import com.sherlock.box.service.UserService;
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
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletedUserById(@PathVariable long id) throws UserNotFoundException {
        return userService.deletedUserById(id);
    }

    @PutMapping
    public void updateUser(@RequestBody User user) throws UserNotFoundException {
        userService.updateUser(user);
    }
}
