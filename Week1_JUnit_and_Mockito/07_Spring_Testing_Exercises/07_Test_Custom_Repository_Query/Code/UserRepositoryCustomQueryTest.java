import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import java.util.List;

@DataJpaTest
public class UserRepositoryCustomQueryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        User user1 = new User(null, "John");
        User user2 = new User(null, "John");
        User user3 = new User(null, "Jane");

        entityManager.persistAndFlush(user1);
        entityManager.persistAndFlush(user2);
        entityManager.persistAndFlush(user3);

        List<User> result = userRepository.findByName("John");

        assertEquals(2, result.size());
    }

    @Test
    public void testFindByNameEmpty() {
        List<User> result = userRepository.findByName("NonExistent");

        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindByNameSingleResult() {
        User user = new User(null, "Alice");
        entityManager.persistAndFlush(user);

        List<User> result = userRepository.findByName("Alice");

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    public void testFindByEmailCustomQuery() {
        User user = new User(null, "Bob", "bob@example.com");
        entityManager.persistAndFlush(user);

        User result = userRepository.findByEmail("bob@example.com");

        assertNotNull(result);
        assertEquals("Bob", result.getName());
    }

    @Test
    public void testFindByEmailNotFound() {
        User result = userRepository.findByEmail("notfound@example.com");

        assertNull(result);
    }

    @Test
    public void testMultipleQueries() {
        User user1 = new User(null, "Charlie", "charlie@example.com");
        User user2 = new User(null, "Charlie", "charlie2@example.com");

        entityManager.persistAndFlush(user1);
        entityManager.persistAndFlush(user2);

        List<User> byName = userRepository.findByName("Charlie");
        User byEmail = userRepository.findByEmail("charlie@example.com");

        assertEquals(2, byName.size());
        assertNotNull(byEmail);
        assertEquals("charlie@example.com", byEmail.getEmail());
    }
}
