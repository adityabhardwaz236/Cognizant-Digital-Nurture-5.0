import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;

public class PerformanceTesterTest {

    private PerformanceTester performanceTester = new PerformanceTester();

    @Test
    @Timeout(100)
    void testQuickTaskCompletesWithinTimeLimit() {
        performanceTester.performQuickTask();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testTaskCompletesWithinOneSecond() {
        performanceTester.performTask();
    }

    @Test
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void testSlowTaskCompletesWithinFiveSeconds() {
        performanceTester.performSlowTask();
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    void testComplexCalculationCompletesQuickly() {
        int result = performanceTester.calculateComplexNumber();
        assertTrue(result > 0, "Result should be positive");
    }
}
