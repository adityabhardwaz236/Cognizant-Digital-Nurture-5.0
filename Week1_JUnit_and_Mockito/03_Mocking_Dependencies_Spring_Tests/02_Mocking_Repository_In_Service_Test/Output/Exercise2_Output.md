# Exercise 2: Mocking a Repository in a Service Test - Output

## Test Architecture
```
UserServiceTest (Test Layer)
    ↓
UserService (Application Layer) - [Tested]
    ↓
@Mock UserRepository (Mocked Dependency)
    ↓
Database (Not accessed)
```

## Test Execution Results

### UserServiceTest
```
Test Run: UserServiceTest

setUp():
  ✓ Test fixture created
  ✓ testUser = User(1L, "John Doe", "john@example.com")

testGetUserByIdSuccess():
  Setup: Mock repository.findById(1L) → returns Optional.of(testUser)
  Act: userService.getUserById(1L)
  Assert: 
    - Result not null ✓
    - result.getId() = 1L ✓
    - result.getName() = "John Doe" ✓
    - result.getEmail() = "john@example.com" ✓
    - verify(userRepository, times(1)).findById(1L) ✓

testGetUserByIdNotFound():
  Setup: Mock repository.findById(999L) → returns Optional.empty()
  Act: userService.getUserById(999L)
  Assert: 
    - Result is null ✓
    - verify(userRepository, times(1)).findById(999L) ✓

testGetUserByEmail():
  Setup: Mock repository.findByEmail("john@example.com") → returns testUser
  Act: userService.getUserByEmail("john@example.com")
  Assert: 
    - Result not null ✓
    - result.getEmail() = "john@example.com" ✓
    - verify(userRepository, times(1)).findByEmail("john@example.com") ✓

testGetUserByName():
  Setup: Mock repository.findByName("John Doe") → returns testUser
  Act: userService.getUserByName("John Doe")
  Assert: 
    - Result not null ✓
    - result.getName() = "John Doe" ✓
    - verify(userRepository, times(1)).findByName("John Doe") ✓

testSaveUser():
  Setup: Mock repository.save(newUser) → returns newUser
  Act: userService.saveUser(newUser)
  Assert: 
    - Result not null ✓
    - result equals newUser ✓
    - verify(userRepository, times(1)).save(newUser) ✓

testDeleteUser():
  Setup: Mock repository.deleteById(1L) → doNothing()
  Act: userService.deleteUser(1L)
  Assert: 
    - verify(userRepository, times(1)).deleteById(1L) ✓

testUserExists():
  Setup: Mock repository.existsById(1L) → returns true
  Act: userService.userExists(1L)
  Assert: 
    - Result is true ✓
    - verify(userRepository, times(1)).existsById(1L) ✓

testUserNotExists():
  Setup: Mock repository.existsById(999L) → returns false
  Act: userService.userExists(999L)
  Assert: 
    - Result is false ✓
    - verify(userRepository, times(1)).existsById(999L) ✓

Total Tests: 8
Passed: 8
Failed: 0
Duration: ~120 ms
```

## Repository Mocking Patterns

| Pattern | Usage | Purpose |
|---------|-------|---------|
| findById() | when(repo.findById(id)).thenReturn(Optional.of(user)) | Mock JPA find by ID |
| save() | when(repo.save(user)).thenReturn(user) | Mock entity persistence |
| deleteById() | doNothing().when(repo).deleteById(id) | Mock deletion |
| existsById() | when(repo.existsById(id)).thenReturn(true) | Mock existence check |
| Custom finder | when(repo.findByEmail(email)).thenReturn(user) | Mock custom queries |

## Benefits of Testing Service Layer with Mocked Repository
1. ✓ Test business logic without database
2. ✓ Test service behavior with various repository responses
3. ✓ Test error handling (null, empty values)
4. ✓ Fast execution (no I/O operations)
5. ✓ Easy to simulate edge cases
6. ✓ No test database setup required
