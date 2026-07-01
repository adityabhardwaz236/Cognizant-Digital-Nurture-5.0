# Exercise 9: Parameterized Test with JUnit - Output

## Overview
Parameterized tests for testing multiple input scenarios with @ParameterizedTest.

## Test Architecture
```
@ParameterizedTest (Multiple Inputs)
    ↓
CalculatorService (Single implementation)
    ↓
Multiple executions (One per parameter set)
```

## Test Execution Results

### CalculatorServiceParameterizedTest
```
Test Run: CalculatorServiceParameterizedTest

testEvenNumbers() - @ValueSource
  Input: [2, 4, 6, 8, 10]
  Execution 1: isEven(2) → ✓ PASS
  Execution 2: isEven(4) → ✓ PASS
  Execution 3: isEven(6) → ✓ PASS
  Execution 4: isEven(8) → ✓ PASS
  Execution 5: isEven(10) → ✓ PASS
  Total: 5 tests, all passed

testOddNumbers() - @ValueSource
  Input: [1, 3, 5, 7, 9]
  Execution 1: isEven(1) → false ✓ PASS
  Execution 2: isEven(3) → false ✓ PASS
  Execution 3: isEven(5) → false ✓ PASS
  Execution 4: isEven(7) → false ✓ PASS
  Execution 5: isEven(9) → false ✓ PASS
  Total: 5 tests, all passed

testAddWithMultipleInputs() - @CsvSource
  Input: (5, 3, 8) → add(5, 3) = 8 ✓ PASS
  Input: (0, 0, 0) → add(0, 0) = 0 ✓ PASS
  Input: (-5, -3, -8) → add(-5, -3) = -8 ✓ PASS
  Input: (10, -5, 5) → add(10, -5) = 5 ✓ PASS
  Total: 4 tests, all passed

testDivideWithMultipleInputs() - @CsvSource
  Input: (10, 2, 5) → divide(10, 2) = 5 ✓ PASS
  Input: (15, 3, 5) → divide(15, 3) = 5 ✓ PASS
  Input: (20, 4, 5) → divide(20, 4) = 5 ✓ PASS
  Input: (100, 10, 10) → divide(100, 10) = 10 ✓ PASS
  Total: 4 tests, all passed

testMultiplyWithMultipleInputs() - @CsvSource
  Input: (4, 5, 20) → multiply(4, 5) = 20 ✓ PASS
  Input: (3, 3, 9) → multiply(3, 3) = 9 ✓ PASS
  Input: (0, 10, 0) → multiply(0, 10) = 0 ✓ PASS
  Input: (-2, 5, -10) → multiply(-2, 5) = -10 ✓ PASS
  Total: 4 tests, all passed

testSubtractWithMultipleInputs() - @CsvSource
  Input: (10, 5, 5) → subtract(10, 5) = 5 ✓ PASS
  Input: (20, 10, 10) → subtract(20, 10) = 10 ✓ PASS
  Input: (-5, 3, -8) → subtract(-5, 3) = -8 ✓ PASS
  Input: (0, 0, 0) → subtract(0, 0) = 0 ✓ PASS
  Total: 4 tests, all passed

testGetMaxWithMultipleInputs() - @CsvSource
  Input: (10, 5, 10) → getMax(10, 5) = 10 ✓ PASS
  Input: (3, 7, 7) → getMax(3, 7) = 7 ✓ PASS
  Input: (-5, -10, -5) → getMax(-5, -10) = -5 ✓ PASS
  Input: (0, 0, 0) → getMax(0, 0) = 0 ✓ PASS
  Total: 4 tests, all passed

Grand Total: 30 parameterized test executions
Passed: 30
Failed: 0
Duration: ~120 ms
```

## @ParameterizedTest Annotations

| Annotation | Purpose | Example |
|-----------|---------|---------|
| @ParameterizedTest | Mark as parameterized | Replace @Test |
| @ValueSource | Single value per test | @ValueSource(ints={2,4,6}) |
| @CsvSource | CSV format multiple values | @CsvSource({"5,3,8", "0,0,0"}) |
| @MethodSource | Method provides data | @MethodSource("methodName") |
| @DisplayName | Custom test name | @DisplayName("Custom name") |

## Parameterized Test Syntax

### Using @ValueSource
```java
@ParameterizedTest
@ValueSource(ints = {2, 4, 6, 8})
public void testEvenNumbers(int number) {
    assertTrue(calculator.isEven(number));
    // Executes 4 times with each value
}
```

### Using @CsvSource
```java
@ParameterizedTest
@CsvSource({
    "5, 3, 8",      // a=5, b=3, expected=8
    "0, 0, 0",      // a=0, b=0, expected=0
    "-5, -3, -8"    // a=-5, b=-3, expected=-8
})
public void testAdd(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
    // Executes 3 times with each row
}
```

## Benefits of Parameterized Tests

| Benefit | Example |
|---------|---------|
| Reduce code duplication | One method tests 30 cases |
| Better test coverage | Test multiple scenarios |
| Easier to add cases | Just add CSV row |
| Clear intent | Data-driven testing |
| Maintainability | Less boilerplate |

## Test Metrics

```
Traditional Tests:     30 @Test methods = ~300 lines
Parameterized Tests:   7 @ParameterizedTest = ~150 lines
Reduction:             50% less code
Coverage:              Same 30 test cases
```

## Best Practices

1. ✓ Use for multiple input scenarios
2. ✓ Include edge cases (0, negatives, nulls)
3. ✓ Use descriptive parameter names
4. ✓ Keep parameter sets small
5. ✓ Use @DisplayName for clarity
