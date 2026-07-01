# Exercise 5: Timeout and Performance Testing - Output

## Performance/Timeout Testing Results

### PerformanceTesterTest
```
Test Run: PerformanceTesterTest

testQuickTaskCompletesWithinTimeLimit():
  Task Duration: 50 ms
  Timeout Limit: 100 ms
  Status: ✓ PASSED (within limit)

testTaskCompletesWithOneSecond():
  Task Duration: 500 ms
  Timeout Limit: 1000 ms (1 second)
  Status: ✓ PASSED (within limit)

testSlowTaskCompletesWithinFiveSeconds():
  Task Duration: 3000 ms
  Timeout Limit: 5000 ms (5 seconds)
  Status: ✓ PASSED (within limit)

testComplexCalculationCompletesQuickly():
  Calculation Duration: ~20 ms
  Timeout Limit: 100 ms
  Result: 333332833333500 (verified > 0)
  Status: ✓ PASSED (within limit)

Total Tests: 4
Passed: 4
Failed: 0
Total Duration: ~3750 ms
```

## Performance Metrics
```
Test Execution Timeline:
┌─────────────────────────────────────────────────────┐
│ 0 ms    → Quick Task (50ms)                         │
│ 50 ms   → Complex Calculation (20ms)                │
│ 70 ms   → Regular Task (500ms)                      │
│ 570 ms  → Slow Task (3000ms)                        │
│ 3570 ms → Complete                                  │
└─────────────────────────────────────────────────────┘
```

## Timeout Exception Behavior
```
If test exceeds timeout:
  Exception: org.junit.jupiter.api.extension.ExtensionContext$ExecutionCondition
  Result: TEST FAILS
  Message: "Execution exceeded timeout of [X] [TimeUnit]"
```
