import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class DataProcessorTest {

    @Test
    public void testVoidMethod() {

        Logger mockLogger = mock(Logger.class);
        DataProcessor processor = new DataProcessor(mockLogger);

        processor.processData("test data");

        verify(mockLogger).log("Processing data: test data");
        verify(mockLogger).log("Data processed successfully");
    }

    @Test
    public void testHandleErrorVoidMethod() {

        Logger mockLogger = mock(Logger.class);
        DataProcessor processor = new DataProcessor(mockLogger);

        processor.handleError("Database connection failed");

        verify(mockLogger).error("An error occurred: Database connection failed");
    }

    @Test
    public void testDebugVoidMethod() {

        Logger mockLogger = mock(Logger.class);
        DataProcessor processor = new DataProcessor(mockLogger);

        processor.executeDebugMode("Variable x = 10");

        verify(mockLogger).debug("Debug info: Variable x = 10");
    }

    @Test
    public void testVoidMethodNotCalled() {

        Logger mockLogger = mock(Logger.class);
        DataProcessor processor = new DataProcessor(mockLogger);

        verify(mockLogger, never()).log(anyString());
    }

    @Test
    public void testVoidMethodWithDoNothing() {

        Logger mockLogger = mock(Logger.class);
        doNothing().when(mockLogger).log(anyString());
        DataProcessor processor = new DataProcessor(mockLogger);

        processor.processData("data");

        verify(mockLogger, atLeast(1)).log(anyString());
    }

    @Test
    public void testProcessWithValidationVoid() {

        Logger mockLogger = mock(Logger.class);
        DataProcessor processor = new DataProcessor(mockLogger);

        processor.processWithValidation("valid");

        verify(mockLogger).log("Valid data: valid");
        verify(mockLogger, never()).error(anyString());
    }

    @Test
    public void testProcessWithValidationErrorVoid() {

        Logger mockLogger = mock(Logger.class);
        DataProcessor processor = new DataProcessor(mockLogger);

        processor.processWithValidation("");

        verify(mockLogger).error("Invalid data provided");
        verify(mockLogger, never()).log(anyString());
    }
}
