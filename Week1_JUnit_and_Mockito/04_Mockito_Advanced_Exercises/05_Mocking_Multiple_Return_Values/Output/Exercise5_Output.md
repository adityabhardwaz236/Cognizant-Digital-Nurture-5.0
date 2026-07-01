# Exercise 5: Mocking Multiple Return Values - Output

## Test Architecture
```
MultiReturnServiceTest (Test Layer)
    ↓
Service (Application Layer) - [Tested]
    ↓
@Mock Repository (Returns different values on consecutive calls)
    ↓
Data Source (Not accessed)
```

## Test Execution Results

### MultiReturnServiceTest
```
Test Run: MultiReturnServiceTest

testServiceWithMultipleReturnValues():
  Arrange: Mock repository.getData()
    .thenReturn("First Mock Data")
    .thenReturn("Second Mock Data")
  Act: 
    - First call: service.processData()
    - Second call: service.processData()
  Assert: 
    - firstResult equals "Processed First Mock Data" ✓
    - secondResult equals "Processed Second Mock Data" ✓
    - verify(mockRepository, times(2)).getData() ✓

testProcessMultipleValuesSequentially():
  Arrange: Mock repository.getData()
    .thenReturn("Value1")
    .thenReturn("Value2")
    .thenReturn("Value3")
  Act: service.processMultipleValues()
  Assert: 
    - results[0] equals "Processed Value1" ✓
    - results[1] equals "Processed Value2" ✓
    - results[2] equals "Processed Value3" ✓
    - verify(mockRepository, times(3)).getData() ✓

testGetSequentialData():
  Arrange: Mock repository.getNextValue() → returns "Next1"
  Act: service.getSequentialData()
  Assert: 
    - Result equals "Next1" ✓
    - verify(mockRepository, times(1)).getNextValue() ✓

testGetThreeSequentialValues():
  Arrange: Mock repository.getNextValue()
    .thenReturn("First")
    .thenReturn("Second")
    .thenReturn("Third")
  Act: service.getThreeSequentialValues()
  Assert: 
    - values[0] equals "First" ✓
    - values[1] equals "Second" ✓
    - values[2] equals "Third" ✓
    - verify(mockRepository, times(3)).getNextValue() ✓

testInterleaveMultipleSources():
  Arrange: 
    - Mock repository.getData() → returns "DataValue"
    - Mock repository.getNextValue() → returns "NextValue"
    - Mock repository.fetchRecord() → returns "RecordValue"
  Act: service.interleaveMultipleSources()
  Assert: 
    - results[0] equals "DataValue" ✓
    - results[1] equals "NextValue" ✓
    - results[2] equals "RecordValue" ✓
    - All verifications passed ✓

testMultipleCallsWithDifferentValues():
  Arrange: Mock repository.getData()
    .thenReturn("Data1")
    .thenReturn("Data2")
    .thenReturn("Data3")
    .thenReturn("Data4")
    .thenReturn("Data5")
  Act: Call service.processData() 5 times
  Assert: 
    - All results match expected values ✓
    - verify(mockRepository, times(5)).getData() ✓

testSequentialThenReturnException():
  Arrange: Mock repository.getData()
    .thenReturn("Success")
    .thenThrow(new RuntimeException("Error"))
  Act: 
    - First call: service.processData()
    - Second call: service.processData() [throws exception]
  Assert: 
    - First result equals "Processed Success" ✓
    - Second call throws RuntimeException ✓
    - verify(mockRepository, times(2)).getData() ✓

testComplexScenarioMultipleReturnsAndMethods():
  Arrange: 
    - Mock getData(): .thenReturn("Data1").thenReturn("Data2")
    - Mock getNextValue(): .thenReturn("Next1").thenReturn("Next2").thenReturn("Next3")
  Act: 
    - Call getThreeSequentialValues()
    - Call processData() twice
  Assert: 
    - All results match expected values ✓
    - Verification counts correct ✓

Total Tests: 8
Passed: 8
Failed: 0
Duration: ~120 ms
```

## Multiple Return Values Patterns

| Pattern | Code | Use Case |
|---------|------|----------|
| Sequential Returns | when(...).thenReturn(val1).thenReturn(val2) | Different values on consecutive calls |
| Chain Returns | .thenReturn().thenReturn().thenReturn() | Multiple sequential calls |
| Interleaved Methods | Mock different methods with own returns | Multiple methods with different values |
| Exception After Success | .thenReturn().thenThrow() | Simulate error after success |

## Syntax for Multiple Return Values

```java
// Syntax for consecutive calls with different returns
when(mock.method())
    .thenReturn("First")
    .thenReturn("Second")
    .thenReturn("Third")
    .thenThrow(new Exception("Error"));

// Call sequence:
// 1st call → "First"
// 2nd call → "Second"
// 3rd call → "Third"
// 4th call → throws Exception
```

## Benefits of Multiple Return Value Mocking
1. ✓ Test sequences of operations
2. ✓ Simulate state changes
3. ✓ Test consecutive calls differently
4. ✓ Test error conditions after success
5. ✓ Test realistic scenarios
6. ✓ Combine multiple return types
