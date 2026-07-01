import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleCalculatorTest {

    private SimpleCalculator calculator;

    @Before
    public void setUp() {
        System.out.println("Setting up test...");
        calculator = new SimpleCalculator();
    }

    @After
    public void tearDown() {
        System.out.println("Tearing down test...");
        calculator = null;
    }

    @Test
    public void testAddition() {

        int initialValue = 10;
        int valueToAdd = 5;
        int expectedResult = 15;

        calculator.add(initialValue);
        calculator.add(valueToAdd);
        int actualResult = calculator.getResult();

        assertEquals("Addition result should be correct", expectedResult, actualResult);
    }

    @Test
    public void testSubtraction() {

        int initialValue = 20;
        int valueToSubtract = 7;
        int expectedResult = 13;

        calculator.add(initialValue);
        calculator.subtract(valueToSubtract);
        int actualResult = calculator.getResult();

        assertEquals("Subtraction result should be correct", expectedResult, actualResult);
    }

    @Test
    public void testReset() {

        calculator.add(100);
        int expectedAfterReset = 0;

        calculator.reset();
        int actualResult = calculator.getResult();

        assertEquals("Result should be zero after reset", expectedAfterReset, actualResult);
    }

    @Test
    public void testMultipleOperations() {

        int expectedResult = 15;

        calculator.add(10);
        calculator.add(20);
        calculator.subtract(5);
        calculator.add(10);
        calculator.subtract(10);
        calculator.add(5);
        calculator.subtract(5);
        calculator.add(10);
        calculator.add(5);
        int actualResult = calculator.getResult();

        assertEquals("Multiple operations should produce correct result", expectedResult, actualResult);
    }

    @Test
    public void testFirstInstance() {

        int result = calculator.getResult();

        assertEquals("Result should be 0 at start (fresh fixture)", 0, result);
    }

    @Test
    public void testSecondInstance() {

        int result = calculator.getResult();

        assertEquals("Result should be 0 at start (fresh fixture)", 0, result);
    }
}
