import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Application started - debug message");
        logger.info("===== Application Initialization =====");

        performDatabaseOperations();
        processUserRequests();
        handleErrors();

        logger.info("===== Application Shutdown =====");
    }

    public static void performDatabaseOperations() {
        logger.info("Connecting to database...");
        logger.debug("Database URL: jdbc:mysql://localhost:3306/appdb");

        try {
            logger.info("Executing query: SELECT * FROM users");
            logger.debug("Query executed successfully");
            logger.info("Retrieved 500 records from database");
        } catch (Exception e) {
            logger.error("Database operation failed", e);
        }
    }

    public static void processUserRequests() {
        logger.info("Processing user requests...");

        for (int i = 1; i <= 5; i++) {
            logger.debug("Processing request {}", i);

            if (i % 2 == 0) {
                logger.warn("Request {} has warnings", i);
            } else {
                logger.info("Request {} processed successfully", i);
            }
        }
    }

    public static void handleErrors() {
        logger.info("Starting error handling test...");

        try {
            validateInput("");
        } catch (IllegalArgumentException e) {
            logger.error("Validation error: {}", e.getMessage(), e);
        }

        try {
            int[] arr = new int[5];
            int value = arr[10];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Array access error occurred", e);
        }

        try {
            String data = null;
            int length = data.length();
        } catch (NullPointerException e) {
            logger.error("NullPointer exception caught", e);
        }

        logger.info("Error handling test completed");
    }

    public static void validateInput(String input) {
        if (input == null || input.isEmpty()) {
            logger.warn("Invalid input provided: empty string");
            throw new IllegalArgumentException("Input cannot be empty");
        }
        logger.info("Input validation passed: {}", input);
    }

    public static void logSystemMetrics() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        logger.info("System Metrics - Total Memory: {} MB, Used: {} MB, Free: {} MB",
                   totalMemory / (1024 * 1024),
                   usedMemory / (1024 * 1024),
                   freeMemory / (1024 * 1024));

        if (usedMemory > totalMemory * 0.8) {
            logger.warn("Memory usage is above 80%");
        }
    }

    public static void logConfigurationSettings() {
        logger.info("===== Configuration Settings =====");
        logger.info("App Name: SLF4J Logging Demo");
        logger.info("App Version: 1.0");
        logger.info("Log Level: DEBUG");
        logger.debug("Configuration loaded from: logback.xml");
        logger.info("Active Appenders: Console, File, RollingFile, ErrorFile");
        logger.info("===== End Configuration =====");
    }
}
