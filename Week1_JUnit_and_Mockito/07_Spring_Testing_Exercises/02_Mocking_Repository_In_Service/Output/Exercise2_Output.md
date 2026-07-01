# Exercise 2: Mocking a Repository in a Service Test - Output

## Overview
Unit tests for UserService with mocked UserRepository dependency.

## Test Architecture
```
UserServiceTest (Test Layer)
    ↓
UserService (Service Layer) - [Tested]
    ↓
@Mock UserRepository (Mocked Dependency)
    ↓
Database (Not accessed)
```

## Test Execution Results

### UserServiceTest
```
Test Run: UserServiceTest

testGetUserById():
  Setup: Mock returns User(1L, "John Doe")
  Action: service.getUserById(1L)
  Expected: User found with name "John Doe"
  Verification: Mock called once
  Result: ✓ PASS

testGetUserByIdNotFound():
  Setup: Mock returns empty Optional
  Action: service.getUserById(999L)
  Expected: null returned
  Verification: Mock called once
  Result: ✓ PASS

testGetUserByEmail():
  Setup: Mock returns specific user
  Action: service.getUserByEmail("john@example.com")
  Expected: User found with email
  Verification: Mock called once
  Result: ✓ PASS

testSaveUser():
  Setup: Mock saves and returns user
  Action: service.saveUser(user)
  Expected: User saved successfully
  Verification: Mock called once with user
  Result: ✓ PASS

testDeleteUser():
  Setup: Mock doNothing() for delete
  Action: service.deleteUser(1L)
  Expected: Delete operation executed
  Verification: deleteById called once with 1L
  Result: ✓ PASS

testGetTotalUsers():
  Setup: Mock count() returns 5
  Action: service.getTotalUsers()
  Expected: 5 users returned
  Verification: count() called once
  Result: ✓ PASS

Total Tests: 6
Passed: 6
Failed: 0
Duration: ~75 ms
```

## Mocking Patterns

| Pattern | Method | Purpose |
|---------|--------|---------|
| Mock | mock(UserRepository.class) | Create mock repository |
| Stub Return | when(...).thenReturn(...) | Define mock behavior |
| Stub Void | doNothing().when(...) | Handle void methods |
| Verify | verify(mock, times(n)) | Verify method calls |
| MockitoAnnotations | @Mock | Annotation-based mocking |

## Benefits of Testing with Mocks

1. ✓ No database needed
2. ✓ Fast test execution
3. ✓ Isolated service testing
4. ✓ Predictable results
5. ✓ Easy error simulation
