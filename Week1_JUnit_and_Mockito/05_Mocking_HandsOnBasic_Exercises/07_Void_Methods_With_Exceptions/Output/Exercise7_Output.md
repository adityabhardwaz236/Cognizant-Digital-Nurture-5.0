# Exercise 7: Void Methods with Exceptions - Output

## Overview
Testing void methods that throw exceptions and verifying error handling.

## Test Architecture
```
DataWriterTest (Test Layer)
    ↓
DataWriter (Application Layer) - [Tested]
    ↓
@Mock FileWriter & @Mock Logger (Exception Handling)
    ↓
File System & Logger (Not accessed)
```

## Test Execution Results

### DataWriterTest
```
Test Run: DataWriterTest

testVoidMethodThrowsException():
  Setup: 
    - Mock FileWriter
    - doThrow(RuntimeException).when(mockFileWriter).writeToFile()
  Action: writer.writeData("test.txt", "data")
  Verification: 
    - verify(mockLogger).log("Writing to file: test.txt")
    - verify(mockLogger).error(contains("Error writing to file"))
  Result: ✓ PASS - Exception handled correctly

testVoidMethodSuccessful():
  Setup: 
    - Mock FileWriter
    - doNothing().when(mockFileWriter).writeToFile()
  Action: writer.writeData("test.txt", "data")
  Verification: 
    - verify(mockLogger).log("Writing to file: test.txt")
    - verify(mockLogger).log("Successfully wrote data")
    - verify(mockLogger, never()).error()
  Result: ✓ PASS - Success path verified

testCloseFileThrowsException():
  Setup: 
    - Mock FileWriter
    - doThrow(RuntimeException).when(mockFileWriter).closeFile()
  Action: writer.closeFile("test.txt")
  Verification: verify(mockLogger).error("Error closing file: test.txt")
  Result: ✓ PASS - Close failure handled

testProcessFileOperationVoidExceptions():
  Setup: 
    - Mock FileWriter
    - Both methods: doNothing()
  Action: writer.processFileOperation("test.txt", "data")
  Verification: 
    - verify(mockFileWriter).writeToFile("test.txt", "data")
    - verify(mockFileWriter).closeFile("test.txt")
  Result: ✓ PASS - Both operations verified

testMultipleVoidMethodExceptions():
  Setup: 
    - Both methods throw exceptions
  Action: 
    - writer.writeData("test.txt", "data")
    - writer.closeFile("test.txt")
  Verification: verify(mockLogger, atLeast(2)).error(anyString())
  Result: ✓ PASS - Multiple errors handled

testVoidMethodPartialException():
  Setup: 
    - writeToFile: doNothing()
    - closeFile: doThrow(RuntimeException)
  Action: writer.processFileOperation("success.txt", "data")
  Verification: Both methods called
  Result: ✓ PASS - Partial failure scenario

Total Tests: 6
Passed: 6
Failed: 0
Duration: ~95 ms
```

## Void Method Exception Patterns

| Pattern | Syntax | Purpose |
|---------|--------|---------|
| Throw Exception | doThrow(exception).when(mock).method() | Simulate error |
| Do Nothing | doNothing().when(mock).method() | Normal behavior |
| Stub Void | Can't use when() for void | Must use do*(). |
| Verify Call | verify(mock).method() | Confirm execution |
| Exception Handling | Try-catch or assertions | Handle thrown exception |

## Exception Stubbing Syntax

```java
// Make void method throw exception
doThrow(new RuntimeException("Error"))
  .when(mockFileWriter)
  .writeToFile(anyString(), anyString());

// Make void method do nothing (default)
doNothing()
  .when(mockFileWriter)
  .writeToFile(anyString(), anyString());

// Verify void method was called despite exception
verify(mockFileWriter).writeToFile(anyString(), anyString());
```

## Key Concepts

1. **Exception Stubbing** - Void methods throwing exceptions
2. **Error Handling** - Testing exception scenarios
3. **Recovery Logic** - Testing error recovery paths
4. **Partial Failure** - One operation succeeds, another fails
5. **Error Verification** - Confirming error methods called
