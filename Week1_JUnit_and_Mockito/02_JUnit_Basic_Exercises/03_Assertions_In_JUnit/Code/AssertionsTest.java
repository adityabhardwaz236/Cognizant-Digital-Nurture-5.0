import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertEquals() {

        int expected = 5;
        int actual = 2 + 3;
        assertEquals("2 + 3 should equal 5", expected, actual);
    }

    @Test
    public void testAssertTrue() {

        int value = 10;
        assertTrue("Value should be greater than 5", value > 5);
    }

    @Test
    public void testAssertFalse() {

        int value = 3;
        assertFalse("Value should not be greater than 5", value > 5);
    }

    @Test
    public void testAssertNull() {

        String nullString = null;
        assertNull("String should be null", nullString);
    }

    @Test
    public void testAssertNotNull() {

        String message = "Hello JUnit";
        assertNotNull("String should not be null", message);
    }

    @Test
    public void testAssertSame() {

        Object obj1 = new Object();
        Object obj2 = obj1;
        assertSame("Objects should be the same", obj1, obj2);
    }

    @Test
    public void testAssertNotSame() {

        Object obj1 = new Object();
        Object obj2 = new Object();
        assertNotSame("Objects should not be the same", obj1, obj2);
    }

    @Test
    public void testAssertArrayEquals() {

        int[] expected = {1, 2, 3, 4, 5};
        int[] actual = {1, 2, 3, 4, 5};
        assertArrayEquals("Arrays should be equal", expected, actual);
    }

    @Test
    public void testMultipleAssertions() {

        String message = "JUnit Testing";

        assertEquals("Message length should be 13", 13, message.length());
        assertNotNull("Message should not be null", message);
        assertTrue("Message should contain 'JUnit'", message.contains("JUnit"));
        assertTrue("Message should start with 'J'", message.startsWith("J"));
        assertTrue("Message should end with 'g'", message.endsWith("g"));
    }
}
