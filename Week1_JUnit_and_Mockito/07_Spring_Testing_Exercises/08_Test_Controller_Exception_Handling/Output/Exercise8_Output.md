# Exercise 8: Test Controller Exception Handling - Output

## Overview
Tests for @ControllerAdvice global exception handling.

## Test Architecture
```
UserControllerExceptionTest (Test Layer - @WebMvcTest)
    ↓
GlobalExceptionHandler (@ControllerAdvice)
    ↓
UserController (REST Layer)
    ↓
@MockBean UserService (Mock throwing exceptions)
```

## Exception Handler Mappings

```
Exception Type         → HTTP Status
NoSuchElementException → 404 Not Found
IllegalArgumentException → 400 Bad Request
Generic Exception     → 500 Internal Server Error
```

## Test Execution Results

### UserControllerExceptionTest
```
Test Run: UserControllerExceptionTest

testGetUserNotFound():
  Request: GET /users/999
  Mock Setup: Service throws NoSuchElementException
  Exception Handler: GlobalExceptionHandler.handleNotFound()
  Expected Response:
    Status: 404 Not Found
    Body: "User not found"
  Result: ✓ PASS

testHandleNoSuchElementException():
  Setup: Service throws NoSuchElementException
  Expected:
    Status: 404 Not Found
    Exception caught by handler
  Result: ✓ PASS

testHandleIllegalArgumentException():
  Setup: Service throws IllegalArgumentException("Invalid user data")
  Expected:
    Status: 400 Bad Request
    Body: "Invalid argument: Invalid user data"
  Result: ✓ PASS

testHandleGenericException():
  Setup: Service throws RuntimeException("Database error")
  Expected:
    Status: 500 Internal Server Error
    Body: "An error occurred"
  Result: ✓ PASS

testSuccessAfterException():
  Request 1: GET /users/1 - throws exception
  Expected: 500 Internal Server Error
  Request 2: GET /users/1 - returns user
  Expected: 200 OK with user
  Result: ✓ PASS - Error recovery works

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~90 ms
```

## Global Exception Handler

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    
    // 404 Not Found
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("User not found");
    }
    
    // 400 Bad Request
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Invalid argument: " + ex.getMessage());
    }
    
    // 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An error occurred");
    }
}
```

## Exception Handling Flow

```
Controller throws Exception
         ↓
@ControllerAdvice intercepts
         ↓
@ExceptionHandler matches exception type
         ↓
Handler method executes
         ↓
ResponseEntity returned
         ↓
Client receives HTTP response with status & message
```

## HTTP Status Codes

| Status | Exception | Meaning |
|--------|-----------|---------|
| 400 | IllegalArgumentException | Bad request |
| 404 | NoSuchElementException | Resource not found |
| 500 | RuntimeException | Server error |
| 403 | AccessDeniedException | Permission denied |
| 409 | DataIntegrityViolationException | Conflict |

## Testing Exception Responses

```java
// Test exception is thrown
when(userService.getUserById(999L))
    .thenThrow(new NoSuchElementException());

// Verify response
mockMvc.perform(get("/users/999"))
    .andExpect(status().isNotFound());

// Verify response body
mockMvc.perform(get("/users/999"))
    .andExpect(content().string("User not found"));
```

## Benefits

1. ✓ Centralized error handling
2. ✓ Consistent error responses
3. ✓ HTTP status mapping
4. ✓ Error message customization
5. ✓ Easy to test error scenarios
