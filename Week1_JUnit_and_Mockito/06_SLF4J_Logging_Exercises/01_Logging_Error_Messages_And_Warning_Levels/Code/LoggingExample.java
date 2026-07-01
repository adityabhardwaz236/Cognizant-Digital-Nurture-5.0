import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message: Application started");
        logger.info("Info message: Processing data");
        logger.warn("This is a warning message");
        logger.error("This is an error message");

        processData("Sample Data");
        handleException();
    }

    public static void processData(String data) {
        logger.info("Processing data: " + data);
        if (data == null || data.isEmpty()) {
            logger.error("Data processing failed: Empty data provided");
        } else {
            logger.info("Data processed successfully");
        }
    }

    public static void handleException() {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("Caught exception: Division by zero", e);
            logger.warn("Attempting recovery");
        }
    }

    public static void validateUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            logger.error("User validation failed: Invalid user ID");
        } else if (userId.length() < 3) {
            logger.warn("User ID is short: " + userId);
        } else {
            logger.info("User validation passed: " + userId);
        }
    }

    public static void logApplicationStatus() {
        logger.info("===== Application Status Report =====");
        logger.info("Status: Running");
        logger.info("Version: 1.0");
        logger.warn("Memory usage is high");
        logger.info("===== End of Report =====");
    }
}
