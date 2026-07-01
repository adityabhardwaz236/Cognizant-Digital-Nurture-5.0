# Exercise 1: Basic Unit Test for a Service Method - Output

## Overview
Unit tests for a simple calculator service method.

## Test Architecture
```
CalculatorServiceTest (Test Layer)
    ↓
CalculatorService (Service Layer) - [Tested]
    ↓
Math Operations (No dependencies)
```

## Test Execution Results

### CalculatorServiceTest
```
Test Run: CalculatorServiceTest

testAdd():
  Input: add(5, 3)
  Expected: 8
  Result: ✓ PASS

testAddNegativeNumbers():
  Input: add(-5, -3)
  Expected: -8
  Result: ✓ PASS

testAddMixedNumbers():
  Input: add(-5, 3)
  Expected: -2
  Result: ✓ PASS

testSubtract():
  Input: subtract(10, 3)
  Expected: 7
  Result: ✓ PASS

testMultiply():
  Input: multiply(4, 5)
  Expected: 20
  Result: ✓ PASS

testMultiplyWithZero():
  Input: multiply(4, 0)
  Expected: 0
  Result: ✓ PASS

testDivide():
  Input: divide(10, 2)
  Expected: 5
  Result: ✓ PASS

testDivideByZero():
  Input: divide(10, 0)
  Expected: IllegalArgumentException
  Result: ✓ PASS - Exception thrown correctly

testIsEven():
  Input: isEven(4), isEven(5)
  Expected: true, false
  Result: ✓ PASS

testGetMax():
  Input: getMax(10, 5), getMax(5, 10)
  Expected: 10, 10
  Result: ✓ PASS

Total Tests: 11
Passed: 11
Failed: 0
Duration: ~50 ms
```

## Service Methods and Assertions

| Method | Input | Expected | Assertion Type |
|--------|-------|----------|-----------------|
| add() | (5, 3) | 8 | assertEquals |
| subtract() | (10, 3) | 7 | assertEquals |
| multiply() | (4, 5) | 20 | assertEquals |
| divide() | (10, 2) | 5 | assertEquals |
| divide() | (10, 0) | Exception | assertThrows |
| isEven() | 4 | true | assertTrue |
| isEven() | 5 | false | assertFalse |
| getMax() | (10, 5) | 10 | assertEquals |

## Key Testing Concepts

1. ✓ **@BeforeEach** - Setup method before each test
2. ✓ **assertEquals()** - Assert expected vs actual values
3. ✓ **assertTrue/assertFalse()** - Assert boolean conditions
4. ✓ **assertThrows()** - Assert exception is thrown
5. ✓ **Test Method Naming** - Descriptive test names
