# Exercise 1: Logging Error Messages and Warning Levels - Output

## Overview
Demonstrates basic SLF4J logging with different log levels (DEBUG, INFO, WARN, ERROR).

## SLF4J Log Levels (from least to most severe)
```
1. DEBUG  - Detailed information for debugging
2. INFO   - General informational messages
3. WARN   - Warning messages
4. ERROR  - Error messages
5. FATAL  - Fatal errors (highest priority)
```

## Expected Console Output

```
[main] DEBUG com.example.LoggingExample - Debug message: Application started
[main] INFO com.example.LoggingExample - Info message: Processing data
[main] WARN com.example.LoggingExample - This is a warning message
[main] ERROR com.example.LoggingExample - This is an error message
[main] INFO com.example.LoggingExample - Processing data: Sample Data
[main] INFO com.example.LoggingExample - Data processed successfully
[main] ERROR com.example.LoggingExample - Caught exception: Division by zero
java.lang.ArithmeticException: / by zero
    at LoggingExample.handleException(LoggingExample.java:35)
[main] WARN com.example.LoggingExample - Attempting recovery
```

## Code Methods and Their Output

### 1. main() Method
```
Logs at various levels:
- DEBUG: Application started
- INFO: Processing data
- WARN: Warning message
- ERROR: Error message
```

### 2. processData(String data)
```
Input: "Sample Data"
Output:
  INFO: Processing data: Sample Data
  INFO: Data processed successfully
```

### 3. handleException()
```
Output:
  ERROR: Caught exception: Division by zero (with stacktrace)
  WARN: Attempting recovery
```

### 4. validateUser(String userId)
```
Example 1 - Invalid User:
  Input: null or ""
  ERROR: User validation failed: Invalid user ID

Example 2 - Short User ID:
  Input: "ab"
  WARN: User ID is short: ab

Example 3 - Valid User:
  Input: "user123"
  INFO: User validation passed: user123
```

## Key Concepts

| Level | Use Case | Example |
|-------|----------|---------|
| DEBUG | Detailed troubleshooting info | Variable values, method entry/exit |
| INFO | General informational | Application started, data processed |
| WARN | Warning conditions | Resource warnings, deprecated features |
| ERROR | Error events | Exceptions, operation failures |

## Maven Dependencies

```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.30</version>
</dependency>
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

## Benefits of SLF4J

1. ✓ **Abstraction** - Facade over multiple logging frameworks
2. ✓ **Performance** - Lazy evaluation with parameterized messages
3. ✓ **Flexibility** - Easy to switch implementations
4. ✓ **Standard** - Widely used in Java ecosystem
5. ✓ **Exception Logging** - Built-in support for exception details
