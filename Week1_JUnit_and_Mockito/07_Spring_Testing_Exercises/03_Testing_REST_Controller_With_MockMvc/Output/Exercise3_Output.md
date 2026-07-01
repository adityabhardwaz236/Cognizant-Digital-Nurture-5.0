# Exercise 3: Testing a REST Controller with MockMvc - Output

## Overview
Tests for REST controller endpoints using MockMvc.

## Test Architecture
```
UserControllerTest (Test Layer - @WebMvcTest)
    ↓
UserController (REST Layer) - [Tested]
    ↓
@MockBean UserService (Mocked)
    ↓
Service Layer (Not accessed)
```

## Test Execution Results

### UserControllerTest
```
Test Run: UserControllerTest

testGetUserSuccess():
  Request: GET /users/1
  Mock Setup: Service returns User(1L, "John Doe")
  Expected Response:
    Status: 200 OK
    Body: JSON with id=1, name="John Doe"
  Verification: getUserById(1L) called once
  Result: ✓ PASS

testGetUserNotFound():
  Request: GET /users/999
  Mock Setup: Service returns null
  Expected Response:
    Status: 404 Not Found
  Verification: getUserById(999L) called once
  Result: ✓ PASS

testCreateUser():
  Request: POST /users with JSON body
  Mock Setup: Service saves and returns user
  Expected Response:
    Status: 200 OK
    Body: Saved user JSON
  Verification: saveUser called once
  Result: ✓ PASS

testDeleteUser():
  Request: DELETE /users/1
  Mock Setup: Service doNothing()
  Expected Response:
    Status: 204 No Content
  Verification: deleteUser(1L) called once
  Result: ✓ PASS

testGetTotalUsers():
  Request: GET /users
  Mock Setup: Service returns 5
  Expected Response:
    Status: 200 OK
    Body: Integer value 5
  Verification: getTotalUsers() called once
  Result: ✓ PASS

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~100 ms
```

## HTTP Request/Response Patterns

| HTTP Method | Endpoint | Expected Status | Purpose |
|------------|----------|-----------------|---------|
| GET | /users/{id} | 200/404 | Retrieve user |
| POST | /users | 200 | Create user |
| DELETE | /users/{id} | 204 | Delete user |
| GET | /users | 200 | Get total count |

## MockMvc Methods

| Method | Purpose |
|--------|---------|
| .perform() | Execute HTTP request |
| .andExpect() | Assert response |
| .jsonPath() | Assert JSON properties |
| status() | Assert HTTP status |

## Key Assertions

```java
// Status assertions
.andExpect(status().isOk())           // 200
.andExpect(status().isNotFound())     // 404
.andExpect(status().isNoContent())    // 204

// JSON assertions
.andExpect(jsonPath("$.id").value(1))
.andExpect(jsonPath("$.name").value("John"))

// Verify mock interactions
verify(userService, times(1)).getUserById(1L)
```

## Benefits

1. ✓ Test controllers without server startup
2. ✓ Verify HTTP status codes
3. ✓ Assert JSON response content
4. ✓ Mock service dependencies
5. ✓ Fast integration-style tests
