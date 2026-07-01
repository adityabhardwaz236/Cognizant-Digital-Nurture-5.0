# Exercise 3: Mocking a Service Dependency in an Integration Test - Output

## Test Architecture
```
UserIntegrationTest (Integration Test Layer)
    ↓
@SpringBootTest (Full Application Context)
    ↓
UserController (Application Layer) - [Tested via HTTP]
    ↓
@MockBean UserService (Mocked Dependency)
    ↓
UserRepository (Not accessed, service is mocked)
```

## Test Execution Results

### UserIntegrationTest
```
Test Run: UserIntegrationTest

Test Environment:
  ✓ @SpringBootTest - Full application context loaded
  ✓ @AutoConfigureMockMvc - MockMvc configured for HTTP testing
  ✓ ObjectMapper - JSON serialization/deserialization ready

testGetUserSuccess():
  Setup: Mock userService.getUserById(1L) → returns User(1, "John Doe", "john@example.com")
  Act: MockMvc.perform(GET /users/1)
  Assert: 
    - HTTP Status: 200 OK ✓
    - Response JSON $.id: 1 ✓
    - Response JSON $.name: "John Doe" ✓
    - Response JSON $.email: "john@example.com" ✓
    - verify(userService, times(1)).getUserById(1L) ✓

testGetUserNotFound():
  Setup: Mock userService.getUserById(999L) → returns null
  Act: MockMvc.perform(GET /users/999)
  Assert: 
    - HTTP Status: 404 NOT FOUND ✓
    - verify(userService, times(1)).getUserById(999L) ✓

testCreateUser():
  Setup: Mock userService.saveUser(any(User.class)) → returns User(2, "Jane Smith", "jane@example.com")
  Act: MockMvc.perform(POST /users 
       with JSON body: {"id":2, "name":"Jane Smith", "email":"jane@example.com"})
  Assert: 
    - HTTP Status: 200 OK ✓
    - Response JSON $.id: 2 ✓
    - Response JSON $.name: "Jane Smith" ✓
    - verify(userService, times(1)).saveUser(any(User.class)) ✓

testDeleteUser():
  Setup: Mock userService.deleteUser(1L) → doNothing()
  Act: MockMvc.perform(DELETE /users/1)
  Assert: 
    - HTTP Status: 200 OK ✓
    - verify(userService, times(1)).deleteUser(1L) ✓

testMultipleGetRequests():
  Setup: Mock userService.getUserById(1L) → returns User(1, "Test User", "test@example.com")
  Act: 
    - MockMvc.perform(GET /users/1) - First request
    - MockMvc.perform(GET /users/1) - Second request
  Assert: 
    - First request HTTP Status: 200 OK ✓
    - Second request HTTP Status: 200 OK ✓
    - verify(userService, times(2)).getUserById(1L) ✓

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~250 ms
```

## Integration Test Key Annotations

| Annotation | Purpose |
|-----------|---------|
| @SpringBootTest | Loads full application context for integration testing |
| @AutoConfigureMockMvc | Auto-configures MockMvc for testing HTTP layer |
| @MockBean | Replaces bean in application context with a mock |
| @RunWith(SpringRunner.class) | JUnit 4 test runner for Spring tests |

## MockMvc HTTP Testing Methods

| Method | Usage | Example |
|--------|-------|---------|
| perform() | Execute HTTP request | mockMvc.perform(get("/users/1")) |
| get() | HTTP GET | get("/users/{id}", userId) |
| post() | HTTP POST | post("/users").content(json) |
| delete() | HTTP DELETE | delete("/users/{id}", userId) |
| andExpect() | Assert response | andExpect(status().isOk()) |
| status() | Check HTTP status | status().isOk(), status().isNotFound() |
| jsonPath() | Assert JSON response | jsonPath("$.name").value("John") |

## Request/Response Flow in Integration Test

```
1. TEST REQUEST
   MockMvc → Spring DispatcherServlet → UserController
   
2. CONTROLLER PROCESSING
   UserController.getUser(1L)
   → calls @Mock userService.getUserById(1L)
   → mock returns test data
   → ResponseEntity.ok(user)
   
3. TEST RESPONSE ASSERTION
   MockMvc checks HTTP status, headers, JSON content
```

## Comparison: Unit vs Integration Testing

| Aspect | Unit Test (Exercise 1-2) | Integration Test (Exercise 3) |
|--------|--------------------------|--------------------------------|
| Scope | Single class in isolation | Multiple classes together |
| Context | No Spring context | Full Spring context |
| Database | Mocked repository | Mocked service |
| HTTP Testing | Not tested | Full HTTP tested with MockMvc |
| Speed | Very fast | Slower (more setup) |
| Coverage | Controller/Service logic | Controller → Service integration |

## Benefits of Integration Testing
1. ✓ Tests real HTTP communication
2. ✓ Validates request/response serialization
3. ✓ Tests exception handling across layers
4. ✓ Tests URL mapping and routing
5. ✓ Catches integration issues
6. ✓ More realistic test scenario
