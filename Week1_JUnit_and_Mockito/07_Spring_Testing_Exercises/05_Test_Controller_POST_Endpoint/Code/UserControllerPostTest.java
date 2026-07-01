import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerPostTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        User newUser = new User(null, "New User", "new@example.com");
        User savedUser = new User(1L, "New User", "new@example.com");

        when(userService.saveUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New User"));

        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    public void testCreateUserWithValidData() throws Exception {
        User user = new User(2L, "Jane Smith", "jane.smith@example.com");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Smith"))
                .andExpect(jsonPath("$.email").value("jane.smith@example.com"));

        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    public void testCreateMultipleUsers() throws Exception {
        User user1 = new User(1L, "User 1");
        User user2 = new User(2L, "User 2");

        when(userService.saveUser(any(User.class)))
                .thenReturn(user1)
                .thenReturn(user2);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("User 1"));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("User 2"));

        verify(userService, times(2)).saveUser(any(User.class));
    }

    @Test
    public void testCreateUserWithoutEmail() throws Exception {
        User user = new User(3L, "No Email User");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        verify(userService, times(1)).saveUser(any(User.class));
    }
}
