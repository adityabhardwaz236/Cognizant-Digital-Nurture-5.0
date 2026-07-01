# Exercise 2: Writing Basic JUnit Tests - Output

## Calculator Test Results

### Test Execution

#### CalculatorTest
```
Test Run: CalculatorTest

testAdd():
  ✓ PASSED - Addition: 5 + 3 = 8
  assertEquals(8, calculator.add(5, 3))

testSubtract():
  ✓ PASSED - Subtraction: 10 - 4 = 6
  assertEquals(6, calculator.subtract(10, 4))

testMultiply():
  ✓ PASSED - Multiplication: 5 * 4 = 20
  assertEquals(20, calculator.multiply(5, 4))

testDivide():
  ✓ PASSED - Division: 20 / 4 = 5
  assertEquals(5, calculator.divide(20, 4))

testDivideByZero():
  ✓ PASSED - Exception caught: IllegalArgumentException
  Expected: Divisor cannot be zero
  Actual: Exception thrown as expected

Total Tests: 5
Passed: 5
Failed: 0
Duration: ~45 ms
```

## Test Coverage
| Method | Test Case | Status |
|--------|-----------|--------|
| add() | 5 + 3 = 8 | ✓ PASSED |
| subtract() | 10 - 4 = 6 | ✓ PASSED |
| multiply() | 5 * 4 = 20 | ✓ PASSED |
| divide() | 20 / 4 = 5 | ✓ PASSED |
| divide() | Division by zero | ✓ EXCEPTION CAUGHT |

