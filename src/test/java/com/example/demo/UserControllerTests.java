package com.example.demo;

import com.example.demo.config.controllers.UserController;
import com.example.demo.config.models.User;
import com.example.demo.config.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private User user;

    @BeforeEach
    public void setUp() {
        userController = new UserController(userService);
    }


    @Test
    public void itAddsUser() {
        userController.addUser(user);

        verify(userService).addUser(user);
    }

}
