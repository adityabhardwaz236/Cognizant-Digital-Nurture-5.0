# Exercise 6: Test Service Exception Handling - Output

## Overview
Tests for exception handling scenarios in service layer.

## Test Architecture
```
UserServiceExceptionTest (Test Layer)
    ↓
UserService (Service Layer) - [Tested]
    ↓
@Mock UserRepository (Exception scenarios)
    ↓
Data Layer (Not accessed)
```

## Test Execution Results

### UserServiceExceptionTest
```
Test Run: UserServiceExceptionTest

testGetUserByIdNotFound():
  Setup: Mock returns empty Optional
  Action: service.getUserById(999L)
  Expected: Graceful null return (no exception)
  Result: ✓ PASS - Handles missing user

testGetUserByEmailNotFound():
  Setup: Mock returns null
  Action: service.getUserByEmail("nonexistent@example.com")
  Expected: null returned
  Result: ✓ PASS

testHandleMissingUserGracefully():
  Setup: Mock empty Optional
  Action: Wrapped in assertDoesNotThrow
  Expected: No exception thrown
  Result: ✓ PASS - Graceful handling

testRepositoryThrowsException():
  Setup: Mock throws RuntimeException
  Action: service.getUserById(1L)
  Expected: Exception propagated
  Assertion: assertThrows(RuntimeException.class)
  Result: ✓ PASS

testSaveUserWithNull():
  Setup: Mock throws IllegalArgumentException
  Action: service.saveUser(null)
  Expected: IllegalArgumentException thrown
  Assertion: assertThrows catches exception
  Result: ✓ PASS

testDeleteNonexistentUser():
  Setup: Mock doNothing() for delete
  Action: service.deleteUser(999L)
  Expected: No exception (silent delete)
  Assertion: assertDoesNotThrow
  Result: ✓ PASS

Total Tests: 6
Passed: 6
Failed: 0
Duration: ~70 ms
```

## Exception Testing Patterns

| Scenario | Method | Expected |
|----------|--------|----------|
| Missing resource | Optional.empty() | null return |
| Repository error | throws Exception | Exception propagates |
| Null input | throws IllegalArgumentException | Exception caught |
| Valid operation | No exception | Success |

## Exception Assertion Methods

```java
// Assert exception is thrown
assertThrows(Exception.class, () -> {
    service.method();
});

// Assert exception is NOT thrown
assertDoesNotThrow(() -> {
    service.method();
});

// Verify exception message
Exception ex = assertThrows(Exception.class, () -> {
    service.method();
});
assertEquals("Expected message", ex.getMessage());
```

## Common Exception Scenarios

| Exception | Cause | Handling |
|-----------|-------|----------|
| NoSuchElementException | Resource not found | Return null/empty |
| IllegalArgumentException | Invalid input | Throw exception |
| RuntimeException | Database error | Propagate or handle |
| NullPointerException | Null reference | Validate input |

## Error Handling Strategies

1. ✓ **Graceful Degradation** - Return null for missing
2. ✓ **Exception Propagation** - Throw for errors
3. ✓ **Validation** - Check inputs before processing
4. ✓ **Logging** - Log exceptions for debugging
5. ✓ **Recovery** - Attempt recovery if possible
