import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
public class OrderedTests {

    private static int executionOrder = 0;

    @Test
    @Order(1)
    void firstTest() {
        executionOrder++;
        assertEquals(1, executionOrder, "This test should execute first");
        System.out.println("Test 1 executed");
    }

    @Test
    @Order(2)
    void secondTest() {
        executionOrder++;
        assertEquals(2, executionOrder, "This test should execute second");
        System.out.println("Test 2 executed");
    }

    @Test
    @Order(3)
    void thirdTest() {
        executionOrder++;
        assertEquals(3, executionOrder, "This test should execute third");
        System.out.println("Test 3 executed");
    }
}
