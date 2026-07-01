import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {

    @Test
    public void testServiceWithMockFileIO() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
        verify(mockFileReader, times(1)).read();
    }

    @Test
    public void testCopyFileContent() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Source File Content");
        doNothing().when(mockFileWriter).write(anyString());
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        fileService.copyFileContent();

        verify(mockFileReader, times(1)).read();
        verify(mockFileWriter, times(1)).write("Source File Content");
    }

    @Test
    public void testReadAndProcess() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Test Data");
        doNothing().when(mockFileWriter).append(anyString());
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        String result = fileService.readAndProcess();

        assertEquals("File processed successfully", result);
        verify(mockFileReader, times(1)).read();
        verify(mockFileWriter, times(1)).append("Test Data [Processed]");
    }

    @Test
    public void testReadAndProcessEmptyFile() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("");
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        String result = fileService.readAndProcess();

        assertEquals("No content to process", result);
        verify(mockFileReader, times(1)).read();
        verify(mockFileWriter, never()).append(anyString());
    }

    @Test
    public void testGetLineCount() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.countLines()).thenReturn(42);
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        int count = fileService.getLineCount();

        assertEquals(42, count);
        verify(mockFileReader, times(1)).countLines();
    }

    @Test
    public void testReadSpecificLine() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.readLine(5)).thenReturn("Line 5 Content");
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        String line = fileService.readSpecificLine(5);

        assertEquals("Line 5 Content", line);
        verify(mockFileReader, times(1)).readLine(5);
    }

    @Test
    public void testBackupFile() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Original Content");
        doNothing().when(mockFileWriter).write(anyString());
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        fileService.backup();

        verify(mockFileReader, times(1)).read();
        verify(mockFileWriter, times(1)).write("[BACKUP] Original Content");
    }

    @Test
    public void testMultipleFileOperations() {

        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Content");
        when(mockFileReader.countLines()).thenReturn(10);
        doNothing().when(mockFileWriter).write(anyString());
        FileService fileService = new FileService(mockFileReader, mockFileWriter);

        fileService.copyFileContent();
        int lineCount = fileService.getLineCount();

        assertEquals(10, lineCount);
        verify(mockFileReader, times(1)).read();
        verify(mockFileReader, times(1)).countLines();
        verify(mockFileWriter, times(1)).write("Content");
    }
}
