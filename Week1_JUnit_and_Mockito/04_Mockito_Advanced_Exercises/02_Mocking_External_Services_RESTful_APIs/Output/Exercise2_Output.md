# Exercise 2: Mocking External Services (RESTful APIs) - Output

## Test Architecture
```
ApiServiceTest (Test Layer)
    ↓
ApiService (Application Layer) - [Tested]
    ↓
@Mock RestClient (Mocked Dependency)
    ↓
External API Server (Not accessed)
```

## Test Execution Results

### ApiServiceTest
```
Test Run: ApiServiceTest

testServiceWithMockRestClient():
  Arrange: Mock restClient.getResponse() → returns "Mock Response"
  Act: apiService.fetchData()
  Assert: 
    - Result equals "Fetched Mock Response" ✓
    - verify(mockRestClient, times(1)).getResponse() ✓

testSubmitDataToApi():
  Arrange: Mock restClient.postData("Test Data") → returns "Success"
  Act: apiService.submitData("Test Data")
  Assert: 
    - Result equals "Submitted Success" ✓
    - verify(mockRestClient, times(1)).postData("Test Data") ✓

testDeleteResourceFromApi():
  Arrange: Mock restClient.deleteResource("123") → returns true
  Act: apiService.removeResource("123")
  Assert: 
    - Result is true ✓
    - verify(mockRestClient, times(1)).deleteResource("123") ✓

testDeleteResourceNotFound():
  Arrange: Mock restClient.deleteResource("999") → returns false
  Act: apiService.removeResource("999")
  Assert: 
    - Result is false ✓
    - verify(mockRestClient, times(1)).deleteResource("999") ✓

testFetchFromEndpointWithData():
  Arrange: Mock restClient.getData("/users") → returns "User List Data"
  Act: apiService.fetchFromEndpoint("/users")
  Assert: 
    - Result equals "Retrieved from /users: User List Data" ✓
    - verify(mockRestClient, times(1)).getData("/users") ✓

testFetchFromEndpointNoData():
  Arrange: Mock restClient.getData("/empty") → returns ""
  Act: apiService.fetchFromEndpoint("/empty")
  Assert: 
    - Result equals "No data from /empty" ✓
    - verify(mockRestClient, times(1)).getData("/empty") ✓

testGetWeatherData():
  Arrange: Mock restClient.getData("/weather") → returns "Sunny, 25C"
  Act: apiService.getWeatherData()
  Assert: 
    - Result equals "Weather: Sunny, 25C" ✓
    - verify(mockRestClient, times(1)).getData("/weather") ✓

testMultipleApiCalls():
  Arrange: 
    - Mock restClient.getResponse() → returns "Response 1"
    - Mock restClient.postData("Data") → returns "Posted"
  Act: 
    - apiService.fetchData()
    - apiService.submitData("Data")
  Assert: 
    - First result equals "Fetched Response 1" ✓
    - Second result equals "Submitted Posted" ✓
    - verify(mockRestClient, times(1)).getResponse() ✓
    - verify(mockRestClient, times(1)).postData("Data") ✓

Total Tests: 8
Passed: 8
Failed: 0
Duration: ~100 ms
```

## RESTful API Mocking Patterns

| Method | REST Operation | Mock Pattern |
|--------|-----------------|--------------|
| getResponse() | GET | when(...).thenReturn() |
| postData() | POST | when(...).thenReturn() |
| deleteResource() | DELETE | when(...).thenReturn(boolean) |
| getData(endpoint) | GET with path | when(...).thenReturn() |

## Benefits of Mocking REST Clients
1. ✓ No actual network calls needed
2. ✓ Tests run offline
3. ✓ Fast test execution
4. ✓ Simulate API errors easily
5. ✓ Control HTTP responses
6. ✓ Test multiple scenarios
