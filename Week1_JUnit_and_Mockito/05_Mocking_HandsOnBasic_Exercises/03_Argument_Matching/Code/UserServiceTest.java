import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void testVerifySpecificArguments() {

        UserRepository mockRepository = mock(UserRepository.class);
        UserService service = new UserService(mockRepository);

        service.createUser("user123", "John Doe");

        verify(mockRepository).saveUser("user123", "John Doe");
    }

    @Test
    public void testVerifyWithAnyString() {

        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.getUserName(anyString())).thenReturn("User Name");
        UserService service = new UserService(mockRepository);

        String result = service.getUserName("user123");

        assertEquals("User Name", result);
        verify(mockRepository).getUserName("user123");
    }

    @Test
    public void testVerifyDeleteWithSpecificId() {

        UserRepository mockRepository = mock(UserRepository.class);
        UserService service = new UserService(mockRepository);

        service.removeUser("user456");

        verify(mockRepository).deleteUser("user456");
    }

    @Test
    public void testVerifyArgumentMatcher() {

        UserRepository mockRepository = mock(UserRepository.class);
        when(mockRepository.userExists(startsWith("admin"))).thenReturn(true);
        UserService service = new UserService(mockRepository);

        boolean exists = service.checkUserExists("admin123");

        assertTrue(exists);
        verify(mockRepository).userExists("admin123");
    }

    @Test
    public void testVerifyMultipleArgumentsMatchers() {

        UserRepository mockRepository = mock(UserRepository.class);
        UserService service = new UserService(mockRepository);

        service.createUser("user789", "Jane Smith");

        verify(mockRepository).saveUser(contains("user"), contains("Jane"));
    }

    @Test
    public void testVerifyNotCalledWithSpecificArguments() {

        UserRepository mockRepository = mock(UserRepository.class);
        UserService service = new UserService(mockRepository);

        service.removeUser("user111");

        verify(mockRepository, never()).deleteUser("user222");
    }
}
