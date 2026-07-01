# Exercise 2: Parameterized Logging - Output

## Overview
Demonstrates parameterized logging using placeholders ({}) instead of string concatenation.

## Why Parameterized Logging?

### Without Parameterization (Less Efficient)
```java
logger.info("User " + userId + " has been activated");
```
- String concatenation happens regardless of log level
- Wastes CPU and memory resources

### With Parameterization (More Efficient)
```java
logger.info("User {} has been activated", userId);
```
- Arguments only evaluated if log level is enabled
- Much faster performance
- Better memory efficiency

## Expected Console Output

```
[main] INFO com.example.ParameterizedLoggingExample - Application started with version 1.0
[main] INFO com.example.ParameterizedLoggingExample - Processing user: ID=user123, Name=John Doe
[main] DEBUG com.example.ParameterizedLoggingExample - User details retrieved from database
[main] INFO com.example.ParameterizedLoggingExample - User user123 has been activated
[main] INFO com.example.ParameterizedLoggingExample - Transaction ID: 1001, Amount: 500.0, Status: COMPLETED
[main] DEBUG com.example.ParameterizedLoggingExample - Transaction 1001 processed successfully
[main] ERROR com.example.ParameterizedLoggingExample - Database error occurred on table: USER_TABLE
java.lang.RuntimeException: Connection timeout
[main] WARN com.example.ParameterizedLoggingExample - Retrying connection to table: USER_TABLE
[main] INFO com.example.ParameterizedLoggingExample - Batch operation: 85 items processed out of 100
[main] DEBUG com.example.ParameterizedLoggingExample - Progress: 85.00%
[main] WARN com.example.ParameterizedLoggingExample - Batch operation incomplete: 15 remaining
[main] INFO com.example.ParameterizedLoggingExample - Performance Metrics - Response Time: 1500ms, Active Users: 200, CPU: 95.5%
[main] WARN com.example.ParameterizedLoggingExample - High response time detected: 1500ms
[main] ERROR com.example.ParameterizedLoggingExample - Critical CPU usage: 95.5%
```

## Parameterized Logging Examples

### Single Parameter
```java
logger.info("User {} logged in", userId);
// Output: User user123 logged in
```

### Multiple Parameters
```java
logger.info("Transaction: {}, Amount: {}, Status: {}", 
            transactionId, amount, status);
// Output: Transaction: 1001, Amount: 500.0, Status: COMPLETED
```

### With Conditional Logic
```java
if (amount > 1000) {
    logger.warn("Large transaction detected: {} for amount {}", 
                transactionId, amount);
}
```

### With Exception
```java
logger.error("Database error occurred on table: {}", tableName, e);
// Automatically includes exception stacktrace
```

## Code Methods and Outputs

### 1. processUser(String userId, String userName)
```
Input: "user123", "John Doe"
Output:
  INFO: Processing user: ID=user123, Name=John Doe
  DEBUG: User details retrieved from database
  INFO: User user123 has been activated
```

### 2. processTransaction(int id, double amount, String status)
```
Input: 1001, 500.00, "COMPLETED"
Output:
  INFO: Transaction ID: 1001, Amount: 500.0, Status: COMPLETED
  DEBUG: Transaction 1001 processed successfully

Input: 1002, 2000.00, "PENDING"
Output:
  INFO: Transaction ID: 1002, Amount: 2000.0, Status: PENDING
  WARN: Large transaction detected: 1002 for amount 2000.0
```

### 3. performBatchOperation(int total, int processed)
```
Input: 100, 85
Output:
  INFO: Batch operation: 85 items processed out of 100
  DEBUG: Progress: 85.00%
  WARN: Batch operation incomplete: 15 remaining
```

### 4. logPerformanceMetrics(long time, int users, double cpu)
```
Input: 1500, 200, 95.5
Output:
  INFO: Performance Metrics - Response Time: 1500ms, Active Users: 200, CPU: 95.5%
  WARN: High response time detected: 1500ms
  ERROR: Critical CPU usage: 95.5%
```

## Parameterization Performance Comparison

| Scenario | With Concatenation | With Parameterization |
|----------|-------------------|----------------------|
| String building | Always happens | Only if level enabled |
| Method calls | All evaluated | Lazy evaluation |
| Memory usage | Higher | Lower |
| Performance | Slower | Faster |
| Readability | Hard to format | Clean and readable |

## Best Practices

1. ✓ Always use parameterized logging
2. ✓ Use appropriate log levels
3. ✓ Include context in messages
4. ✓ Pass exceptions as last argument
5. ✓ Avoid sensitive data in logs
