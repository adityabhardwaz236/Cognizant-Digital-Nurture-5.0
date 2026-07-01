import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorServiceParameterizedTest {

    private CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    public void testEvenNumbers(int number) {
        assertTrue(calculatorService.isEven(number), number + " should be even");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 9})
    public void testOddNumbers(int number) {
        assertFalse(calculatorService.isEven(number), number + " should be odd");
    }

    @ParameterizedTest
    @CsvSource({
        "5, 3, 8",
        "0, 0, 0",
        "-5, -3, -8",
        "10, -5, 5"
    })
    public void testAddWithMultipleInputs(int a, int b, int expected) {
        int result = calculatorService.add(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "10, 2, 5",
        "15, 3, 5",
        "20, 4, 5",
        "100, 10, 10"
    })
    public void testDivideWithMultipleInputs(int a, int b, int expected) {
        int result = calculatorService.divide(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "4, 5, 20",
        "3, 3, 9",
        "0, 10, 0",
        "-2, 5, -10"
    })
    public void testMultiplyWithMultipleInputs(int a, int b, int expected) {
        int result = calculatorService.multiply(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "10, 5, 5",
        "20, 10, 10",
        "-5, 3, 3",
        "0, 0, 0"
    })
    public void testSubtractWithMultipleInputs(int a, int b, int expected) {
        int result = calculatorService.subtract(a, b);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "10, 5, 10",
        "3, 7, 7",
        "-5, -10, -5",
        "0, 0, 0"
    })
    public void testGetMaxWithMultipleInputs(int a, int b, int expected) {
        int result = calculatorService.getMax(a, b);
        assertEquals(expected, result);
    }
}
