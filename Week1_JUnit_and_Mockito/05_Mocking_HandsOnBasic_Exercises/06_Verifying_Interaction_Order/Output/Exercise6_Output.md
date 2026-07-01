# Exercise 6: Verifying Interaction Order - Output

## Overview
Ensuring methods are called in a specific order using InOrder verification.

## Test Architecture
```
DataServiceTest (Test Layer)
    ↓
DataService (Application Layer) - [Tested]
    ↓
@Mock Database + @Mock Cache (Order Verified)
    ↓
Database & Cache Systems (Not accessed)
```

## Test Execution Results

### DataServiceTest
```
Test Run: DataServiceTest

testVerifyInteractionOrder():
  Setup: 
    - Mock Database and Cache
    - Create InOrder verifier
    - Cache returns null (cache miss)
  Action: service.retrieveData("SELECT *")
  Expected Interaction Order:
    1. cache.retrieve("query_SELECT *")
    2. database.fetchData("SELECT *")
    3. cache.store("query_SELECT *", "Data Result")
  Verification: InOrder inOrder = inOrder(mockCache, mockDatabase)
    - inOrder.verify(mockCache).retrieve()
    - inOrder.verify(mockDatabase).fetchData()
    - inOrder.verify(mockCache).store()
  Result: ✓ PASS - Interactions verified in correct order

testUpdateDataFlowOrder():
  Setup: Mock Database and Cache
  Action: service.updateDataFlow("New Data")
  Expected Order:
    1. database.saveData("New Data")
    2. cache.store("latest", "New Data")
  Verification: InOrder confirms this order
  Result: ✓ PASS - Database save before cache store

testCacheHitSkipsDatabase():
  Setup: 
    - Mock with cache returning "Cached Data"
    - Database not to be called
  Action: service.retrieveData("SELECT *")
  Verification: 
    - verify(mockCache).retrieve()
    - verify(mockDatabase, never()).fetchData()
  Result: ✓ PASS - Database call skipped on cache hit

testMethodCallSequence():
  Setup: Mock Database
  Action: service.retrieveData("test")
  Verification: Database method called
  Result: ✓ PASS - Method verified

Total Tests: 4
Passed: 4
Failed: 0
Duration: ~100 ms
```

## Interaction Order Verification

| Verification | Syntax | Purpose |
|--------------|--------|---------|
| Create Order | InOrder inOrder = inOrder(mock1, mock2) | Set up order verifier |
| Verify First | inOrder.verify(mock).method1() | First interaction |
| Verify Second | inOrder.verify(mock).method2() | Second interaction |
| Verify Third | inOrder.verify(mock).method3() | Third interaction |
| Check Sequence | Full InOrder chain | Entire interaction flow |

## Interaction Order Pattern Example

```java
// Setup
Database mockDb = mock(Database.class);
Cache mockCache = mock(Cache.class);
InOrder inOrder = inOrder(mockCache, mockDb);

// Execute
service.retrieveData("query");

// Verify Order
inOrder.verify(mockCache).retrieve("key");      // First
inOrder.verify(mockDb).fetchData("query");      // Second
inOrder.verify(mockCache).store("key", data);   // Third
```

## Key Concepts

1. **Interaction Order** - Sequence of method calls matters
2. **InOrder Verification** - Enforce specific call sequences
3. **State Flow** - Cache then database pattern
4. **Dependency Sequence** - Operations must happen in order
