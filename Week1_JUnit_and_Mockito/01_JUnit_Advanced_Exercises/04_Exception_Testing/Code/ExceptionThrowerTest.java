import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionThrowerTest {

    private ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void testThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            exceptionThrower.throwException();
        });
    }

    @Test
    void testThrowExceptionWithMessage() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> exceptionThrower.throwException()
        );
        assertEquals("This is a test exception", exception.getMessage());
    }

    @Test
    void testThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            exceptionThrower.throwExceptionIfNull(null);
        });
    }

    @Test
    void testValidateAgeWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            exceptionThrower.validateAge(-5);
        });
    }

    @Test
    void testValidateAgeWithPositiveValue() {
        assertDoesNotThrow(() -> {
            exceptionThrower.validateAge(25);
        });
    }
}
