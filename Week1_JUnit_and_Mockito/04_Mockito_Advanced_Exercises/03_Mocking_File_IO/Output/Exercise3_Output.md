# Exercise 3: Mocking File I/O - Output

## Test Architecture
```
FileServiceTest (Test Layer)
    ↓
FileService (Application Layer) - [Tested]
    ↓
@Mock FileReader & @Mock FileWriter (Mocked Dependencies)
    ↓
Disk Storage (Not accessed)
```

## Test Execution Results

### FileServiceTest
```
Test Run: FileServiceTest

testServiceWithMockFileIO():
  Arrange: 
    - Mock fileReader.read() → returns "Mock File Content"
    - Mock fileWriter (unused in this test)
  Act: fileService.processFile()
  Assert: 
    - Result equals "Processed Mock File Content" ✓
    - verify(mockFileReader, times(1)).read() ✓

testCopyFileContent():
  Arrange: 
    - Mock fileReader.read() → returns "Source File Content"
    - Mock fileWriter.write(anyString()) → doNothing()
  Act: fileService.copyFileContent()
  Assert: 
    - verify(mockFileReader, times(1)).read() ✓
    - verify(mockFileWriter, times(1)).write("Source File Content") ✓

testReadAndProcess():
  Arrange: 
    - Mock fileReader.read() → returns "Test Data"
    - Mock fileWriter.append(anyString()) → doNothing()
  Act: fileService.readAndProcess()
  Assert: 
    - Result equals "File processed successfully" ✓
    - verify(mockFileReader, times(1)).read() ✓
    - verify(mockFileWriter, times(1)).append("Test Data [Processed]") ✓

testReadAndProcessEmptyFile():
  Arrange: 
    - Mock fileReader.read() → returns ""
    - Mock fileWriter not called
  Act: fileService.readAndProcess()
  Assert: 
    - Result equals "No content to process" ✓
    - verify(mockFileWriter, never()).append(anyString()) ✓

testGetLineCount():
  Arrange: Mock fileReader.countLines() → returns 42
  Act: fileService.getLineCount()
  Assert: 
    - Result equals 42 ✓
    - verify(mockFileReader, times(1)).countLines() ✓

testReadSpecificLine():
  Arrange: Mock fileReader.readLine(5) → returns "Line 5 Content"
  Act: fileService.readSpecificLine(5)
  Assert: 
    - Result equals "Line 5 Content" ✓
    - verify(mockFileReader, times(1)).readLine(5) ✓

testBackupFile():
  Arrange: 
    - Mock fileReader.read() → returns "Original Content"
    - Mock fileWriter.write(anyString()) → doNothing()
  Act: fileService.backup()
  Assert: 
    - verify(mockFileReader, times(1)).read() ✓
    - verify(mockFileWriter, times(1)).write("[BACKUP] Original Content") ✓

testMultipleFileOperations():
  Arrange: 
    - Mock fileReader.read() → returns "Content"
    - Mock fileReader.countLines() → returns 10
    - Mock fileWriter.write(anyString()) → doNothing()
  Act: 
    - fileService.copyFileContent()
    - fileService.getLineCount()
  Assert: 
    - verify(mockFileReader, times(1)).read() ✓
    - verify(mockFileReader, times(1)).countLines() ✓
    - verify(mockFileWriter, times(1)).write("Content") ✓

Total Tests: 8
Passed: 8
Failed: 0
Duration: ~90 ms
```

## File I/O Mocking Patterns

| Operation | Mock Pattern | Purpose |
|-----------|--------------|---------|
| Read File | when(reader.read()).thenReturn(content) | Simulate file reading |
| Write File | doNothing().when(writer).write(anyString()) | Mock write operations |
| Read Line | when(reader.readLine(n)).thenReturn(line) | Get specific line |
| Count Lines | when(reader.countLines()).thenReturn(n) | Get line count |
| Append | doNothing().when(writer).append(anyString()) | Mock append |

## Benefits of Mocking File I/O
1. ✓ No disk I/O overhead
2. ✓ Faster test execution
3. ✓ No files created during tests
4. ✓ Easy error simulation
5. ✓ Test edge cases (empty files, null content)
6. ✓ Portable tests (no file system dependency)
