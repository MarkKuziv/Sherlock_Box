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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    public void setUp(){
        userService = new UserService(userRepository);
    }

    @Test
    public void itAddsUser() {
        userService.addUser(user);

        verify(userRepository).save(user);
    }

    @Test
    public void itUpdatesUser() throws UserNotFoundException {
        when(user.getId()).thenReturn(ID);
        when(userRepository.findUserById(user.getId())).thenReturn(user);

        userService.updateUser(user);

        verify(userRepository).save(user);
    }

    @Test
    public void itThrownUserNotFoundExceptionWhenUserUpdates() {
        when(user.getId()).thenReturn(ID);

        assertThatThrownBy(() -> userService.updateUser(user))
                .hasMessage(String.format("User with %d not found", ID))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void itDeletesUser() throws UserNotFoundException {
        when(userRepository.findUserById(ID)).thenReturn(user);

        userService.deletedUserById(ID);

        verify(userRepository).deleteById(ID);
    }

    @Test
    public void itThrownUserNotFoundExceptionWhenUserIsMissingDeletes() {
        assertThatThrownBy(() -> userService.deletedUserById(ID))
                .hasMessage(String.format("User with %d not found", ID))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void itGetsUser() throws UserNotFoundException {
        when(userRepository.findUserById(ID)).thenReturn(user);

        userService.getUserById(ID);

        verify(userRepository).findUserById(ID);
    }

    @Test
    public void itThrownUserNotFoundExceptionWhenUserIsMissingGets() {
        assertThatThrownBy(() -> userService.getUserById(ID))
                .hasMessage(String.format("User with %d not found", ID))
                .isInstanceOf(UserNotFoundException.class);
    }
}
