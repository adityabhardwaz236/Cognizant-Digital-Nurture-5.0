# Exercise 4: Arrange-Act-Assert (AAA) Pattern & Test Fixtures - Output

## SimpleCalculatorTest Results

### Test Execution

```
Test Setup: SimpleCalculatorTest

@Before setUp() - Runs before each test:
  Setting up test...
  Creating new SimpleCalculator instance

---

testAddition():
  Arrange: initialValue=10, valueToAdd=5, expectedResult=15
  Act: calculator.add(10); calculator.add(5);
  Assert: assertEquals(15, actualResult)
  ✓ PASSED

@After tearDown() - Runs after each test:
  Tearing down test...
  Cleaning up: calculator = null

---

testSubtraction():
  Arrange: initialValue=20, valueToSubtract=7, expectedResult=13
  Act: calculator.add(20); calculator.subtract(7);
  Assert: assertEquals(13, actualResult)
  ✓ PASSED

---

testReset():
  Arrange: calculator.add(100), expectedAfterReset=0
  Act: calculator.reset();
  Assert: assertEquals(0, actualResult)
  ✓ PASSED

---

testMultipleOperations():
  Arrange: Set expectedResult = 15
  Act: Multiple add/subtract operations
  Assert: assertEquals(15, actualResult)
  ✓ PASSED

---

testFirstInstance():
  Arrange: Fresh calculator instance (from @Before)
  Act: int result = calculator.getResult();
  Assert: assertEquals(0, result) - Fresh fixture verified
  ✓ PASSED

---

testSecondInstance():
  Arrange: Fresh calculator instance (from @Before)
  Act: int result = calculator.getResult();
  Assert: assertEquals(0, result) - Fresh fixture verified
  ✓ PASSED

Total Tests: 6
Passed: 6
Failed: 0
Total Duration: ~150 ms
```

## AAA Pattern Breakdown

### Arrange
- Set up test data and objects
- Initialize test fixtures
- Prepare expected values

### Act
- Execute the method being tested
- Perform the action you want to verify

### Assert
- Verify the results using assertions
- Check that actual output matches expected output

## Test Fixture Concept

### @Before Annotation
```
Execution: @Before → @Test → @After
           (Setup)   (Test)  (Cleanup)
```

- Runs BEFORE each test method
- Used to initialize test objects (fixtures)
- Creates fresh instances for each test
- Ensures test isolation

### @After Annotation
- Runs AFTER each test method (even if it fails)
- Used for cleanup/teardown
- Releases resources
- Ensures no state leaks between tests

## Test Isolation Verification
```
Test 1: setUp() → testFirstInstance() → tearDown()
        Fresh fixture: result = 0 ✓

Test 2: setUp() → testSecondInstance() → tearDown()
        Fresh fixture: result = 0 ✓

Each test has its own isolated fixture ✓
```

## Key Benefits
1. **Code Reuse**: @Before/After eliminates duplication
2. **Test Isolation**: Each test gets fresh objects
3. **Clear Structure**: AAA pattern makes tests easy to read
4. **Maintainability**: Setup/teardown centralized
5. **Resource Management**: Proper cleanup prevents side effects
