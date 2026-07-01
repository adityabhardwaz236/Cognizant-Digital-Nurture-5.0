import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        logger.info("Application started with version {}", "1.0");

        processUser("user123", "John Doe");
        processTransaction(1001, 500.00, "COMPLETED");
        handleDatabaseError("USER_TABLE", new RuntimeException("Connection timeout"));

        performBatchOperation(100, 85);
        logPerformanceMetrics(1500, 200, 95.5);
    }

    public static void processUser(String userId, String userName) {
        logger.info("Processing user: ID={}, Name={}", userId, userName);
        logger.debug("User details retrieved from database");
        logger.info("User {} has been activated", userId);
    }

    public static void processTransaction(int transactionId, double amount, String status) {
        logger.info("Transaction ID: {}, Amount: {}, Status: {}", transactionId, amount, status);

        if (amount > 1000) {
            logger.warn("Large transaction detected: {} for amount {}", transactionId, amount);
        } else {
            logger.debug("Transaction {} processed successfully", transactionId);
        }
    }

    public static void handleDatabaseError(String tableName, Exception e) {
        logger.error("Database error occurred on table: {}", tableName, e);
        logger.warn("Retrying connection to table: {}", tableName);
    }

    public static void logLoginAttempt(String username, String ipAddress, boolean success) {
        if (success) {
            logger.info("User {} logged in successfully from IP: {}", username, ipAddress);
        } else {
            logger.warn("Failed login attempt for user {} from IP: {}", username, ipAddress);
        }
    }

    public static void performBatchOperation(int totalItems, int processedItems) {
        logger.info("Batch operation: {} items processed out of {}", processedItems, totalItems);

        double percentage = (processedItems * 100.0) / totalItems;
        logger.debug("Progress: {}%", String.format("%.2f", percentage));

        if (processedItems < totalItems) {
            logger.warn("Batch operation incomplete: {} remaining", (totalItems - processedItems));
        } else {
            logger.info("Batch operation completed: All {} items processed", totalItems);
        }
    }

    public static void logPerformanceMetrics(long responseTime, int activeUsers, double cpuUsage) {
        logger.info("Performance Metrics - Response Time: {}ms, Active Users: {}, CPU: {}%",
                   responseTime, activeUsers, cpuUsage);

        if (responseTime > 1000) {
            logger.warn("High response time detected: {}ms", responseTime);
        }

        if (cpuUsage > 90) {
            logger.error("Critical CPU usage: {}%", cpuUsage);
        } else if (cpuUsage > 75) {
            logger.warn("CPU usage is high: {}%", cpuUsage);
        } else {
            logger.debug("CPU usage is normal: {}%", cpuUsage);
        }
    }

    public static void logApiCall(String endpoint, String method, int statusCode, long duration) {
        logger.info("API Call: {} {} - Status: {} - Duration: {}ms",
                   method, endpoint, statusCode, duration);

        if (statusCode >= 400) {
            logger.error("API error on {} {}: Status {}", method, endpoint, statusCode);
        } else if (statusCode >= 300) {
            logger.warn("API redirect on {} {}: Status {}", method, endpoint, statusCode);
        } else {
            logger.debug("API call successful: {} {} - {}", method, endpoint, statusCode);
        }
    }
}
