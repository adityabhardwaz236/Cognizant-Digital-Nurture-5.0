import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetUserById() {
        User mockUser = new User(1L, "John Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        User result = userService.getUserById(999L);

        assertNull(result);
        verify(userRepository, times(1)).findById(999L);
    }

    @Test
    public void testGetUserByEmail() {
        User mockUser = new User(1L, "John Doe", "john@example.com");
        when(userRepository.findByEmail("john@example.com")).thenReturn(mockUser);

        User result = userService.getUserByEmail("john@example.com");

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        verify(userRepository, times(1)).findByEmail("john@example.com");
    }

    @Test
    public void testSaveUser() {
        User user = new User(1L, "Jane Doe");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetTotalUsers() {
        when(userRepository.count()).thenReturn(5L);

        int total = userService.getTotalUsers();

        assertEquals(5, total);
        verify(userRepository, times(1)).count();
    }
}
