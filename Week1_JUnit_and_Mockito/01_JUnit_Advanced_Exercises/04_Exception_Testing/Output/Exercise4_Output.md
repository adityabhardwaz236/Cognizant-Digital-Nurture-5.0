# Exercise 4: Exception Testing - Output

## Exception Testing Results

### ExceptionThrowerTest
```
Test Run: ExceptionThrowerTest

testThrowException():
  ✓ PASSED - IllegalArgumentException thrown as expected

testThrowExceptionWithMessage():
  ✓ PASSED - Exception message verified: "This is a test exception"

testThrowNullPointerException():
  ✓ PASSED - NullPointerException thrown when null passed

testValidateAgeWithNegativeValue():
  ✓ PASSED - IllegalArgumentException thrown for negative age (-5)
  Exception Message: "Age cannot be negative"

testValidateAgeWithPositiveValue():
  ✓ PASSED - No exception thrown for valid age (25)

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~40 ms
```

## Exception Verification Details
```
1. assertThrows(ExceptionType, () -> code)
   - Verifies that the exception type is thrown
   
2. Capturing Exception for Message Verification
   - Can check exception message for correctness
   
3. assertDoesNotThrow(Executable)
   - Verifies that no exception is thrown
```

