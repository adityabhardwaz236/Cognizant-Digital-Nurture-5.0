import org.junit.Test;
import static org.junit.Assert.*;

public class FirstTestClass {

    @Test
    public void testSetupComplete() {

        int expected = 5;
        int actual = 2 + 3;
        assertEquals("JUnit setup is working correctly", expected, actual);
    }

    @Test
    public void testMultiplication() {
        int result = 4 * 5;
        assertEquals(20, result);
    }

    @Test
    public void testStringComparison() {
        String message = "Hello JUnit";
        assertNotNull("String should not be null", message);
        assertTrue("String should contain 'JUnit'", message.contains("JUnit"));
    }
}
