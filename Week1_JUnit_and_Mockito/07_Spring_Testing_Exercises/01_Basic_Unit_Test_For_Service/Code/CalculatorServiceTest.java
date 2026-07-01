import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testAdd() {
        int result = calculatorService.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    public void testAddNegativeNumbers() {
        int result = calculatorService.add(-5, -3);
        assertEquals(-8, result);
    }

    @Test
    public void testAddMixedNumbers() {
        int result = calculatorService.add(-5, 3);
        assertEquals(-2, result);
    }

    @Test
    public void testSubtract() {
        int result = calculatorService.subtract(10, 3);
        assertEquals(7, result);
    }

    @Test
    public void testMultiply() {
        int result = calculatorService.multiply(4, 5);
        assertEquals(20, result);
    }

    @Test
    public void testMultiplyWithZero() {
        int result = calculatorService.multiply(4, 0);
        assertEquals(0, result);
    }

    @Test
    public void testDivide() {
        int result = calculatorService.divide(10, 2);
        assertEquals(5, result);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculatorService.divide(10, 0);
        });
    }

    @Test
    public void testIsEven() {
        assertTrue(calculatorService.isEven(4));
        assertFalse(calculatorService.isEven(5));
    }

    @Test
    public void testGetMax() {
        assertEquals(10, calculatorService.getMax(10, 5));
        assertEquals(10, calculatorService.getMax(5, 10));
    }
}
