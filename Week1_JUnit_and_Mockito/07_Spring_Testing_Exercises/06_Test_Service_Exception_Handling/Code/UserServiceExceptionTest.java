import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class UserServiceExceptionTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        User result = userService.getUserById(999L);

        assertNull(result);
    }

    @Test
    public void testGetUserByEmailNotFound() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        User result = userService.getUserByEmail("nonexistent@example.com");

        assertNull(result);
    }

    @Test
    public void testHandleMissingUserGracefully() {
        when(userRepository.findById(100L)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> {
            User user = userService.getUserById(100L);
            assertNull(user);
        });
    }

    @Test
    public void testRepositoryThrowsException() {
        when(userRepository.findById(any())).thenThrow(new RuntimeException("Database error"));

        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1L);
        });
    }

    @Test
    public void testSaveUserWithNull() {
        User nullUser = null;
        when(userRepository.save(nullUser)).thenThrow(new IllegalArgumentException("User cannot be null"));

        assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(nullUser);
        });
    }

    @Test
    public void testDeleteNonexistentUser() {
        doNothing().when(userRepository).deleteById(999L);

        assertDoesNotThrow(() -> {
            userService.deleteUser(999L);
        });
    }
}
