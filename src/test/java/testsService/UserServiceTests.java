package testsService;

import com.sherlock.box.exception.UserNotFoundException;
import com.sherlock.box.models.User;
import com.sherlock.box.repositories.UserRepository;
import com.sherlock.box.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    private static final Long ID = 1L;

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @BeforeEach
    private void setUp(){
        userService = new UserService(userRepository);
    }

    @Test
    public void itAddsCar() {
        userService.addUser(user);

        verify(userRepository).save(user);
    }

    @Test
    public void itUpdatesCar() throws UserNotFoundException {
        when(user.getId()).thenReturn(ID);
        when(userRepository.findUserById(user.getId())).thenReturn(user);

        userService.updateUser(user);

        verify(userRepository).save(user);
    }

    @Test
    public void itDeletesCar() throws UserNotFoundException {
        when(userRepository.findUserById(ID)).thenReturn(user);

        userService.deletedUserById(ID);

        verify(userRepository).deleteById(ID);
    }

    @Test
    public void itGotCar() throws UserNotFoundException {
        when(userRepository.findUserById(ID)).thenReturn(user);

        userService.getUserById(ID);

        verify(userRepository).findUserById(ID);
    }
}
