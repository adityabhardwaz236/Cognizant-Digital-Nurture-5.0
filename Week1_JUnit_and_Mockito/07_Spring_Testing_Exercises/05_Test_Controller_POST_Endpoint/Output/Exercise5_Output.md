# Exercise 5: Test Controller POST Endpoint - Output

## Overview
Tests specifically for POST endpoint with request body and JSON serialization.

## Test Architecture
```
UserControllerPostTest (Test Layer - @WebMvcTest)
    ↓
UserController.createUser() (REST Layer) - [Tested]
    ↓
@MockBean UserService.saveUser() (Mocked)
    ↓
Service Layer (Not accessed)
```

## Test Execution Results

### UserControllerPostTest
```
Test Run: UserControllerPostTest

testCreateUserSuccess():
  Request: POST /users
  Body: JSON serialized User object
  Mock Setup: saveUser returns saved user
  Expected Response:
    Status: 200 OK
    Body: Contains id=1, name="New User"
  Verification: saveUser called with any User
  Result: ✓ PASS

testCreateUserWithValidData():
  Request: POST /users with complete user data
  Body: User with id, name, email
  Expected Response:
    Status: 200 OK
    Body: JSON with email="jane.smith@example.com"
  Result: ✓ PASS

testCreateMultipleUsers():
  Request: POST /users twice
  Mock Setup: Sequential returns (user1, user2)
  Expected:
    First response: 200 OK with user1
    Second response: 200 OK with user2
  Verification: saveUser called twice
  Result: ✓ PASS

testCreateUserWithoutEmail():
  Request: POST /users with user (no email)
  Expected Response:
    Status: 200 OK
  Result: ✓ PASS

Total Tests: 4
Passed: 4
Failed: 0
Duration: ~80 ms
```

## POST Request Testing Patterns

| Component | Method | Purpose |
|-----------|--------|---------|
| Request Type | post("/users") | Define HTTP method |
| Content Type | .contentType(MediaType.APPLICATION_JSON) | Set header |
| Request Body | .content(objectMapper.writeValueAsString(...)) | Serialize object |
| Status Assert | .andExpect(status().isOk()) | Verify 200 |
| JSON Assert | .andExpect(jsonPath("$.id").value(1)) | Verify field |

## ObjectMapper Usage

```java
// Serialize Java object to JSON string
String json = objectMapper.writeValueAsString(user);

// POST request with serialized body
mockMvc.perform(post("/users")
    .contentType(MediaType.APPLICATION_JSON)
    .content(json))
    .andExpect(status().isOk());
```

## JSON Path Examples

```java
.andExpect(jsonPath("$.id").value(1))           // Assert field value
.andExpect(jsonPath("$.name").value("John"))    // Assert string
.andExpect(jsonPath("$").isMap())               // Assert type
.andExpect(jsonPath("$.*").isArray())           // Assert array
```

## Request Body Validation

| Scenario | Expected Result |
|----------|-----------------|
| Valid user | 200 OK |
| Missing email | 200 OK (optional) |
| Invalid JSON | 400 Bad Request |
| Null body | 400 Bad Request |

## Best Practices

1. ✓ Test successful creation (200 OK)
2. ✓ Test validation errors (400 Bad Request)
3. ✓ Test duplicate prevention (409 Conflict)
4. ✓ Verify response body
5. ✓ Verify mock interactions
