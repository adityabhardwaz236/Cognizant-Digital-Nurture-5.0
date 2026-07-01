import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class EvenCheckerTest {

    private EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100})
    void testEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number),
                   number + " should be even");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99})
    void testOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number),
                    number + " should be odd");
    }
}
