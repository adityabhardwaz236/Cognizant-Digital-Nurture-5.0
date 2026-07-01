import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        User user = new User(null, "Test User");
        entityManager.persistAndFlush(user);

        User found = userRepository.findById(user.getId()).orElse(null);

        assertNotNull(found);
        assertEquals("Test User", found.getName());
    }

    @Test
    public void testFindByEmail() {
        User user = new User(null, "Jane Doe", "jane@example.com");
        entityManager.persistAndFlush(user);

        User found = userRepository.findByEmail("jane@example.com");

        assertNotNull(found);
        assertEquals("Jane Doe", found.getName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User(null, "Delete Me");
        entityManager.persistAndFlush(user);
        Long userId = user.getId();

        userRepository.deleteById(userId);

        User found = userRepository.findById(userId).orElse(null);
        assertNull(found);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(null, "Original Name");
        entityManager.persistAndFlush(user);

        user.setName("Updated Name");
        entityManager.persistAndFlush(user);

        User found = userRepository.findById(user.getId()).orElse(null);
        assertEquals("Updated Name", found.getName());
    }

    @Test
    public void testCountUsers() {
        User user1 = new User(null, "User 1");
        User user2 = new User(null, "User 2");
        entityManager.persistAndFlush(user1);
        entityManager.persistAndFlush(user2);

        long count = userRepository.count();

        assertTrue(count >= 2);
    }
}
