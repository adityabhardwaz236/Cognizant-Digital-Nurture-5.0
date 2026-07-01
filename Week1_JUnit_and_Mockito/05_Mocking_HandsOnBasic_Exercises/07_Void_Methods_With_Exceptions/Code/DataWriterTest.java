import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class DataWriterTest {

    @Test
    public void testVoidMethodThrowsException() {

        FileWriter mockFileWriter = mock(FileWriter.class);
        Logger mockLogger = mock(Logger.class);
        doThrow(new RuntimeException("Write failed")).when(mockFileWriter).writeToFile(anyString(), anyString());
        DataWriter writer = new DataWriter(mockFileWriter, mockLogger);

        writer.writeData("test.txt", "data");

        verify(mockLogger).log("Writing to file: test.txt");
        verify(mockLogger).error(contains("Error writing to file"));
    }

    @Test
    public void testVoidMethodSuccessful() {

        FileWriter mockFileWriter = mock(FileWriter.class);
        Logger mockLogger = mock(Logger.class);
        doNothing().when(mockFileWriter).writeToFile(anyString(), anyString());
        DataWriter writer = new DataWriter(mockFileWriter, mockLogger);

        writer.writeData("test.txt", "data");

        verify(mockLogger).log("Writing to file: test.txt");
        verify(mockLogger).log("Successfully wrote data");
        verify(mockLogger, never()).error(anyString());
    }

    @Test
    public void testCloseFileThrowsException() {

        FileWriter mockFileWriter = mock(FileWriter.class);
        Logger mockLogger = mock(Logger.class);
        doThrow(new RuntimeException("Close failed")).when(mockFileWriter).closeFile(anyString());
        DataWriter writer = new DataWriter(mockFileWriter, mockLogger);

        writer.closeFile("test.txt");

        verify(mockLogger).error("Error closing file: test.txt");
    }

    @Test
    public void testProcessFileOperationVoidExceptions() {

        FileWriter mockFileWriter = mock(FileWriter.class);
        Logger mockLogger = mock(Logger.class);
        doNothing().when(mockFileWriter).writeToFile(anyString(), anyString());
        doNothing().when(mockFileWriter).closeFile(anyString());
        DataWriter writer = new DataWriter(mockFileWriter, mockLogger);

        writer.processFileOperation("test.txt", "data");

        verify(mockFileWriter).writeToFile("test.txt", "data");
        verify(mockFileWriter).closeFile("test.txt");
    }

    @Test
    public void testMultipleVoidMethodExceptions() {

        FileWriter mockFileWriter = mock(FileWriter.class);
        Logger mockLogger = mock(Logger.class);
        doThrow(new RuntimeException("Write failed")).when(mockFileWriter).writeToFile(anyString(), anyString());
        doThrow(new RuntimeException("Close failed")).when(mockFileWriter).closeFile(anyString());
        DataWriter writer = new DataWriter(mockFileWriter, mockLogger);

        writer.writeData("test.txt", "data");
        writer.closeFile("test.txt");

        verify(mockLogger, atLeast(2)).error(anyString());
    }

    @Test
    public void testVoidMethodPartialException() {

        FileWriter mockFileWriter = mock(FileWriter.class);
        Logger mockLogger = mock(Logger.class);
        doNothing().when(mockFileWriter).writeToFile("success.txt", "data");
        doThrow(new RuntimeException("Close failed")).when(mockFileWriter).closeFile("success.txt");
        DataWriter writer = new DataWriter(mockFileWriter, mockLogger);

        writer.processFileOperation("success.txt", "data");

        verify(mockFileWriter).writeToFile("success.txt", "data");
        verify(mockFileWriter).closeFile("success.txt");
    }
}
