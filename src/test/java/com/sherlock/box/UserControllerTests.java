package com.sherlock.box;

import com.sherlock.box.controllers.UserController;
import com.sherlock.box.exception.CarNotFoundException;
import com.sherlock.box.exception.OrderNotFoundException;
import com.sherlock.box.models.User;
import com.sherlock.box.service.UserService;
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
