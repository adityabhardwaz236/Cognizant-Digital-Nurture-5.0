import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(1L, "John Doe", "john@example.com");
    }

    @Test
    public void testGetUserSuccess() {

        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(testUser);

        ResponseEntity<User> response = userController.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testUser, response.getBody());
        assertEquals("John Doe", response.getBody().getName());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testGetUserNotFound() {

        Long userId = 999L;
        when(userService.getUserById(userId)).thenReturn(null);

        ResponseEntity<User> response = userController.getUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    public void testCreateUser() {

        User newUser = new User(2L, "Jane Smith", "jane@example.com");
        when(userService.saveUser(newUser)).thenReturn(newUser);

        ResponseEntity<User> response = userController.createUser(newUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newUser, response.getBody());
        verify(userService, times(1)).saveUser(newUser);
    }

    @Test
    public void testDeleteUser() {

        Long userId = 1L;
        doNothing().when(userService).deleteUser(userId);

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService, times(1)).deleteUser(userId);
    }

    @Test
    public void testGetUserVerifyMockInteractions() {

        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(testUser);

        userController.getUser(userId);
        userController.getUser(userId);

        verify(userService, times(2)).getUserById(userId);
        verify(userService, never()).saveUser(any());
    }
}
