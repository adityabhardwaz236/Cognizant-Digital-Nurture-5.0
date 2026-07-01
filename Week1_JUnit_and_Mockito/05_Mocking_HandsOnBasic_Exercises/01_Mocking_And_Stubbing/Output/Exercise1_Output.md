# Exercise 1: Mocking and Stubbing - Output

## Overview
Testing a service that depends on an external API by mocking the API and stubbing its methods.

## Test Architecture
```
MyServiceTest (Test Layer)
    ↓
MyService (Application Layer) - [Tested]
    ↓
@Mock ExternalApi (Mocked Dependency)
    ↓
External API Server (Not accessed)
```

## Test Execution Results

### MyServiceTest
```
Test Run: MyServiceTest

testExternalApi():
  Setup: Mock ExternalApi and stub getData() → "Mock Data"
  Action: service.fetchData()
  Expected: "Mock Data"
  Result: ✓ PASS
  
testFetchUserInfo():
  Setup: Mock and stub fetchUserInfo("user123") → "John Doe"
  Action: service.getUserInfo("user123")
  Expected: "John Doe"
  Result: ✓ PASS

testProcessApiData():
  Setup: Mock and stub getData() → "Raw Data"
  Action: service.processApiData()
  Expected: "Processed: Raw Data"
  Result: ✓ PASS

testGetTotalCount():
  Setup: Mock and stub getCount() → 42
  Action: service.getTotalCount()
  Expected: 42
  Result: ✓ PASS

testMultipleStubbedMethods():
  Setup: 
    - Mock and stub getData() → "Data"
    - Mock and stub getCount() → 10
  Action: 
    - service.fetchData()
    - service.getTotalCount()
  Expected: "Data" and 10
  Result: ✓ PASS

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~60 ms
```

## Mocking Patterns Used

| Pattern | Syntax | Purpose |
|---------|--------|---------|
| Create Mock | mock(ExternalApi.class) | Create mock object |
| Stub Method | when(...).thenReturn(...) | Define mock behavior |
| Verify Behavior | Implicit via assertions | Check results |
| Multiple Stubs | Multiple when() calls | Stub different methods |

## Key Concepts

1. **Mocking** - Creating fake objects to replace real dependencies
2. **Stubbing** - Defining what fake objects should return
3. **Isolation** - Testing service without external API
4. **Predictability** - Tests don't depend on API availability
