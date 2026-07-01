import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(1L, "John Doe", "john@example.com");
    }

    @Test
    public void testGetUserByIdSuccess() {

        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(testUser));

        User result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserByIdNotFound() {

        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User result = userService.getUserById(userId);

        assertNull(result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testGetUserByEmail() {

        String email = "john@example.com";
        when(userRepository.findByEmail(email)).thenReturn(testUser);

        User result = userService.getUserByEmail(email);

        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testGetUserByName() {

        String name = "John Doe";
        when(userRepository.findByName(name)).thenReturn(testUser);

        User result = userService.getUserByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
        verify(userRepository, times(1)).findByName(name);
    }

    @Test
    public void testSaveUser() {

        User newUser = new User(2L, "Jane Smith", "jane@example.com");
        when(userRepository.save(newUser)).thenReturn(newUser);

        User result = userService.saveUser(newUser);

        assertNotNull(result);
        assertEquals(newUser, result);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    public void testDeleteUser() {

        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testUserExists() {

        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);

        boolean exists = userService.userExists(userId);

        assertTrue(exists);
        verify(userRepository, times(1)).existsById(userId);
    }

    @Test
    public void testUserNotExists() {

        Long userId = 999L;
        when(userRepository.existsById(userId)).thenReturn(false);

        boolean exists = userService.userExists(userId);

        assertFalse(exists);
        verify(userRepository, times(1)).existsById(userId);
    }
}
