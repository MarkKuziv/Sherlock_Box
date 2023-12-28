package com.example.demo;

import com.example.demo.config.controllers.UserController;
import com.example.demo.config.exception.CarNotFoundException;
import com.example.demo.config.exception.OrderNotFoundException;
import com.example.demo.config.models.User;
import com.example.demo.config.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    private static final Long ID = 1L;

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
    public void itAddsUser() throws CarNotFoundException {
        userController.addUser(user);

        verify(userService).addUser(user);
    }

    @Test
    public void itGetsUser() throws Exception {
        userController.getUserById(ID);

        verify(userService).getUserById(ID);
    }

    @Test
    public void itDeletesUser() throws CarNotFoundException {
        userController.deletedUserById(ID);

        verify(userService).deletedUserById(ID);
    }

    @Test
    public void itUpdatesUser() throws OrderNotFoundException, CarNotFoundException {
        userController.updateUser(user);

        verify(userService).updateUser(user);
    }

}
