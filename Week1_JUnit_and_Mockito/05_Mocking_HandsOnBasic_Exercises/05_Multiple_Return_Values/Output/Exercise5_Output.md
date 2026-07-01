# Exercise 5: Multiple Return Values - Output

## Overview
Mocking methods to return different values on consecutive calls.

## Test Architecture
```
MyServiceTest (Test Layer)
    ↓
MyService (Application Layer) - [Tested]
    ↓
@Mock ExternalApi (Multiple Return Values)
    ↓
External API (Not accessed)
```

## Test Execution Results

### MyServiceTest
```
Test Run: MyServiceTest

testMultipleReturnValuesSequential():
  Setup: Mock with when(mockApi.getData())
    .thenReturn("First")
    .thenReturn("Second")
    .thenReturn("Third")
  Action: service.fetchDataMultipleTimes()
  Expected: "First, Second, Third"
  Verification: verify(mockApi, times(3)).getData()
  Result: ✓ PASS - Returns different value each call

testSequentialRecordsMultipleReturns():
  Setup: Mock with sequential returns for getNextRecord()
    .thenReturn("Record1")
    .thenReturn("Record2")
    .thenReturn("Record3")
  Action: service.getSequentialRecords()
  Expected: Array ["Record1", "Record2", "Record3"]
  Verification: verify(mockApi, times(3)).getNextRecord()
  Result: ✓ PASS - Array populated with sequential values

testMixedDataMultipleReturns():
  Setup: 
    - Mock getData() → "DataValue"
    - Mock getNextRecord() → "RecordValue"
  Action: service.getMixedData()
  Expected: "DataValue | RecordValue"
  Verification: 
    - verify(mockApi, times(1)).getData()
    - verify(mockApi, times(1)).getNextRecord()
  Result: ✓ PASS - Different methods return their values

testMultipleReturnsWithLastException():
  Setup: Mock with
    .thenReturn("Record1")
    .thenReturn("Record2")
    .thenThrow(new RuntimeException("No more records"))
  Action: 
    - Call 1: mockApi.getNextRecord() → "Record1"
    - Call 2: mockApi.getNextRecord() → "Record2"
    - Call 3: mockApi.getNextRecord() → throws exception
  Result: ✓ PASS - Exception thrown on third call

Total Tests: 4
Passed: 4
Failed: 0
Duration: ~90 ms
```

## Multiple Return Value Patterns

| Pattern | Syntax | Purpose |
|---------|--------|---------|
| Sequential Returns | .thenReturn(v1).thenReturn(v2) | Different values each call |
| Chain Returns | Multiple .thenReturn() calls | Multiple consecutive calls |
| Exception After | .thenReturn().thenThrow() | Success then error |
| Different Methods | Separate when() for each | Independent mocks |
| Array of Returns | Multiple calls in sequence | Building result arrays |

## Call Sequence Example

```
Setup:
  when(mock.getData())
    .thenReturn("First")     // 1st call
    .thenReturn("Second")    // 2nd call
    .thenReturn("Third");    // 3rd call

Execution:
  mock.getData()  → "First"
  mock.getData()  → "Second"
  mock.getData()  → "Third"
  mock.getData()  → "Third" (repeats last value)
```

## Key Concepts

1. **Sequential Mocking** - Different return values per call
2. **State Simulation** - Mock changing behavior
3. **Error Scenarios** - Transition from success to failure
4. **Realistic Sequences** - Test complex call patterns
