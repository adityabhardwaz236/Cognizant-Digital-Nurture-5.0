# Exercise 3: Using Different Appenders - Output

## Overview
Demonstrates using multiple appenders in SLF4J/Logback to route logs to different destinations.

## Logback Appenders

### Types of Appenders

| Appender | Destination | Use Case |
|----------|-------------|----------|
| ConsoleAppender | Console/STDOUT | Development, real-time monitoring |
| FileAppender | Single file | Simple file logging |
| RollingFileAppender | Multiple files with rotation | Production logs, archive management |
| AsyncAppender | Async processing | High-performance logging |
| SocketAppender | Remote server | Centralized logging |

## Configuration Breakdown

### logback.xml Structure
```xml
<!-- Console Output -->
<appender name="console" class="...ConsoleAppender">
  Outputs to: Console/Terminal

<!-- File Output -->
<appender name="file" class="...FileAppender">
  Outputs to: app.log

<!-- Rolling File Output -->
<appender name="rollingFile" class="...RollingFileAppender">
  Outputs to: logs/application-YYYY-MM-DD.log (rotates daily)

<!-- Error-only File Output -->
<appender name="errorFile" class="...FileAppender">
  Outputs to: error.log (only ERROR level)

<!-- Root Logger -->
<root level="debug">
  <appender-ref ref="console" />
  <appender-ref ref="file" />
  <appender-ref ref="rollingFile" />
  <appender-ref ref="errorFile" />
</root>
```

## Expected Output Destinations

### Console Output (ConsoleAppender)
```
16:45:23.123 [main] DEBUG o.s.e.AppenderLoggingExample - Application started - debug message
16:45:23.145 [main] INFO  o.s.e.AppenderLoggingExample - ===== Application Initialization =====
16:45:23.201 [main] INFO  o.s.e.AppenderLoggingExample - Connecting to database...
16:45:23.250 [main] INFO  o.s.e.AppenderLoggingExample - Executing query: SELECT * FROM users
...
```

### app.log (FileAppender)
```
16:45:23.123 [main] DEBUG o.s.e.AppenderLoggingExample - Application started - debug message
16:45:23.145 [main] INFO  o.s.e.AppenderLoggingExample - ===== Application Initialization =====
16:45:23.201 [main] INFO  o.s.e.AppenderLoggingExample - Connecting to database...
[Contains ALL messages - DEBUG, INFO, WARN, ERROR]
```

### logs/application-2024-01-15.log (RollingFileAppender)
```
16:45:23.123 [main] DEBUG o.s.e.AppenderLoggingExample - Application started - debug message
16:45:23.145 [main] INFO  o.s.e.AppenderLoggingExample - ===== Application Initialization =====
[New file created daily, old files archived]
[Max 30 days history, max 10MB per file]
```

### error.log (Error-only FileAppender with Filter)
```
16:45:24.500 [main] ERROR o.s.e.AppenderLoggingExample - Validation error: Input cannot be empty
16:45:24.550 [main] ERROR o.s.e.AppenderLoggingExample - Array access error occurred
16:45:24.600 [main] ERROR o.s.e.AppenderLoggingExample - NullPointer exception caught
[Contains ONLY ERROR level messages]
```

## Rolling File Policy Details

```xml
<rollingPolicy class="...SizeAndTimeBasedRollingPolicy">
  <fileNamePattern>logs/application-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
  <!-- Rotates when date changes or file reaches 10MB -->
  
  <maxFileSize>10MB</maxFileSize>
  <!-- Create new file when size exceeds 10MB -->
  
  <maxHistory>30</maxHistory>
  <!-- Keep last 30 days of logs -->
  
  <totalSizeCap>1GB</totalSizeCap>
  <!-- Delete old files when total size exceeds 1GB -->
</rollingPolicy>
```

## Log Layout Pattern

```
%d{HH:mm:ss.SSS}  - Time with milliseconds
[%thread]         - Thread name
%-5level          - Log level (left-aligned, 5 chars)
%logger{36}       - Logger name (max 36 chars)
- %msg%n          - Message and newline
```

Example: `16:45:23.123 [main] INFO  com.example - Application started`

## Code Methods and Their Outputs

### 1. performDatabaseOperations()
```
Console & File Output:
  INFO: Connecting to database...
  DEBUG: Database URL: jdbc:mysql://localhost:3306/appdb
  INFO: Executing query: SELECT * FROM users
  DEBUG: Query executed successfully
  INFO: Retrieved 500 records from database
```

### 2. processUserRequests()
```
Console & File Output:
  INFO: Processing user requests...
  DEBUG: Processing request 1
  INFO: Request 1 processed successfully
  DEBUG: Processing request 2
  WARN: Request 2 has warnings
  DEBUG: Processing request 3
  INFO: Request 3 processed successfully
  [... continues for requests 4-5]
```

### 3. handleErrors()
```
Console & File Output:
  INFO: Starting error handling test...
  ERROR: Validation error: Input cannot be empty

Error File Only:
  ERROR: Validation error: Input cannot be empty
  ERROR: Array access error occurred
  ERROR: NullPointer exception caught
```

## File Structure After Execution

```
Project Root
├── app.log                              (Main log file)
├── error.log                            (Error-only log file)
└── logs/
    ├── application-2024-01-15.log       (Daily rolling file)
    ├── application-2024-01-15.1.log     (When 10MB exceeded)
    ├── application-2024-01-14.log       (Previous day)
    └── [... up to 30 days of history]
```

## Configuration Benefits

| Feature | Benefit |
|---------|---------|
| Multiple Appenders | Separate concerns (console, file, errors) |
| Rolling Policy | Automatic log rotation and archive |
| Filters | Route specific levels to specific appenders |
| Size-based Rotation | Prevent huge log files |
| Date-based Rotation | Organize logs by date |
| Total Size Cap | Manage disk space |
| TTL (Time To Live) | Automatic cleanup of old logs |

## Logger Configuration

```xml
<!-- Root Logger (all loggers) -->
<root level="debug">
  <appender-ref ref="console" />
  <appender-ref ref="file" />
</root>

<!-- Package-specific Logger -->
<logger name="com.slf4j.example" level="info" />
<!-- This logger uses INFO level, not DEBUG -->
```

## Best Practices

1. ✓ Use ConsoleAppender for development
2. ✓ Use RollingFileAppender for production
3. ✓ Separate ERROR logs for monitoring
4. ✓ Configure TTL to manage disk space
5. ✓ Use appropriate patterns for readability
6. ✓ Configure async appenders for high volume
