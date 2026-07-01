# Exercise 4: Integration Test with Spring Boot - Output

## Overview
Integration tests for repository layer accessing actual database.

## Test Architecture
```
UserRepositoryIntegrationTest (Test Layer - @DataJpaTest)
    ↓
TestEntityManager (Test Database)
    ↓
UserRepository (Real JPA)
    ↓
H2 In-Memory Database
```

## Test Execution Results

### UserRepositoryIntegrationTest
```
Test Run: UserRepositoryIntegrationTest (with H2 database)

testSaveAndFindUser():
  Setup: Persist User(null, "Test User")
  Action: Find by id
  Expected: User found with name "Test User"
  Result: ✓ PASS
  Database: Write + Read verified

testFindByEmail():
  Setup: Persist User(null, "Jane Doe", "jane@example.com")
  Action: Find by email
  Expected: User found with email
  Result: ✓ PASS
  Database: Query executed successfully

testDeleteUser():
  Setup: Persist and then delete user
  Action: Find by id after delete
  Expected: User not found (null)
  Result: ✓ PASS
  Database: Delete verified

testUpdateUser():
  Setup: Persist user "Original Name"
  Action: Change name to "Updated Name"
  Expected: Name persisted as updated
  Result: ✓ PASS
  Database: Update verified

testCountUsers():
  Setup: Persist 2 users
  Action: Count all users
  Expected: Count >= 2
  Result: ✓ PASS
  Database: Count query executed

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~200 ms (includes DB operations)
```

## Integration Test Characteristics

| Aspect | Value |
|--------|-------|
| Database | H2 In-Memory (default) |
| Transaction | Auto-rollback after test |
| Persistence | Real JPA operations |
| Speed | Slower than unit tests |
| Coverage | Full database layer |

## TestEntityManager Methods

```java
entityManager.persistAndFlush(user)  // Save and flush
entityManager.find(User.class, id)   // Find by ID
entityManager.remove(user)           // Delete entity
userRepository.findById(id)          // JPA method
```

## Key Differences from Unit Tests

| Unit Tests | Integration Tests |
|------------|-------------------|
| Mock dependencies | Real database |
| Fast execution | Slower execution |
| Test logic only | Test full flow |
| No setup needed | Database setup |
| @Test + mocks | @DataJpaTest |

## Benefits of Integration Testing

1. ✓ Verify actual database operations
2. ✓ Test query methods
3. ✓ Verify entity mappings
4. ✓ Test transaction behavior
5. ✓ Catch database-specific issues
