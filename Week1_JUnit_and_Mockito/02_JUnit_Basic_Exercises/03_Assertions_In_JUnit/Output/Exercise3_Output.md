# Exercise 3: Assertions in JUnit - Output

## Assertion Test Results

### AssertionsTest
```
Test Run: AssertionsTest

testAssertEquals():
  ✓ PASSED - assertEquals(5, 2 + 3)
  Result: 5 = 5 ✓

testAssertTrue():
  ✓ PASSED - assertTrue(10 > 5)
  Result: 10 > 5 = true ✓

testAssertFalse():
  ✓ PASSED - assertFalse(3 > 5)
  Result: 3 > 5 = false ✓

testAssertNull():
  ✓ PASSED - assertNull(null)
  Result: null is null ✓

testAssertNotNull():
  ✓ PASSED - assertNotNull("Hello JUnit")
  Result: "Hello JUnit" is not null ✓

testAssertSame():
  ✓ PASSED - assertSame(obj1, obj2)
  Result: obj1 and obj2 point to same object ✓

testAssertNotSame():
  ✓ PASSED - assertNotSame(obj1, obj2)
  Result: obj1 and obj2 point to different objects ✓

testAssertArrayEquals():
  ✓ PASSED - assertArrayEquals([1,2,3,4,5], [1,2,3,4,5])
  Result: Arrays are equal ✓

testMultipleAssertions():
  ✓ PASSED - Multiple assertions in single test
  1. assertEquals(13, "JUnit Testing".length()) ✓
  2. assertNotNull("JUnit Testing") ✓
  3. assertTrue(contains 'JUnit') ✓
  4. assertTrue(starts with 'J') ✓
  5. assertTrue(ends with 'g') ✓

Total Tests: 9
Passed: 9
Failed: 0
Duration: ~60 ms
```

## JUnit Assertions Reference

| Assertion | Usage | Purpose |
|-----------|-------|---------|
| assertEquals() | assertEquals(expected, actual) | Checks if two values are equal |
| assertTrue() | assertTrue(condition) | Checks if condition is true |
| assertFalse() | assertFalse(condition) | Checks if condition is false |
| assertNull() | assertNull(object) | Checks if object is null |
| assertNotNull() | assertNotNull(object) | Checks if object is not null |
| assertSame() | assertSame(obj1, obj2) | Checks if objects reference same instance |
| assertNotSame() | assertNotSame(obj1, obj2) | Checks if objects reference different instances |
| assertArrayEquals() | assertArrayEquals(expected[], actual[]) | Checks if arrays are equal |

