# Exercise 1: Mocking a Service Dependency in a Controller Test - Output

## Test Architecture
```
UserControllerTest (Test Layer)
    ↓
UserController (Application Layer) - [Tested]
    ↓
@Mock UserService (Mocked Dependency)
    ↓
UserRepository (Not tested in this exercise)
```

## Test Execution Results

### UserControllerTest
```
Test Run: UserControllerTest

setUp():
  ✓ Test fixture created
  ✓ testUser = User(1L, "John Doe", "john@example.com")

testGetUserSuccess():
  Setup: Mock userService.getUserById(1L) → returns testUser
  Act: GET /users/1
  Assert: 
    - HTTP Status: 200 OK ✓
    - Response Body: User(id=1, name="John Doe", email="john@example.com") ✓
    - verify(userService, times(1)).getUserById(1L) ✓

testGetUserNotFound():
  Setup: Mock userService.getUserById(999L) → returns null
  Act: GET /users/999
  Assert: 
    - HTTP Status: 404 NOT FOUND ✓
    - verify(userService, times(1)).getUserById(999L) ✓

testCreateUser():
  Setup: Mock userService.saveUser(newUser) → returns newUser
  Act: POST /users with body: User(2L, "Jane Smith", "jane@example.com")
  Assert: 
    - HTTP Status: 200 OK ✓
    - Response Body: User(id=2, name="Jane Smith") ✓
    - verify(userService, times(1)).saveUser(newUser) ✓

testDeleteUser():
  Setup: Mock userService.deleteUser(1L) → doNothing()
  Act: DELETE /users/1
  Assert: 
    - HTTP Status: 200 OK ✓
    - verify(userService, times(1)).deleteUser(1L) ✓

testGetUserVerifyMockInteractions():
  Setup: Mock userService.getUserById(1L) → returns testUser
  Act: Call getUser(1L) twice
  Assert: 
    - verify(userService, times(2)).getUserById(1L) ✓
    - verify(userService, never()).saveUser(any()) ✓

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~100 ms
```

## Key Mockito Concepts Demonstrated

| Concept | Example | Purpose |
|---------|---------|---------|
| @Mock | @Mock UserService | Creates a mock object of UserService |
| @InjectMocks | @InjectMocks UserController | Injects mocks into the controller |
| when().thenReturn() | when(userService.getUserById(1L)).thenReturn(user) | Defines mock behavior |
| doNothing().when() | doNothing().when(userService).deleteUser(1L) | Mocks void methods |
| verify() | verify(userService, times(1)).getUserById(1L) | Verifies method calls |
| times() | verify(..., times(1)) | Specifies call count |
| never() | verify(..., never()).saveUser(any()) | Verifies method not called |

## Advantages of Mocking the Service
1. ✓ Tests controller logic in isolation
2. ✓ No need for actual database
3. ✓ Fast test execution
4. ✓ Control over service behavior
5. ✓ Test error scenarios easily
