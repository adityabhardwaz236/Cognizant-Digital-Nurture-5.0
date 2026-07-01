import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyServiceTest {

    @Test
    public void testMultipleReturnValuesSequential() {

        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData())
                .thenReturn("First")
                .thenReturn("Second")
                .thenReturn("Third");
        MyService service = new MyService(mockApi);

        String result = service.fetchDataMultipleTimes();

        assertEquals("First, Second, Third", result);
        verify(mockApi, times(3)).getData();
    }

    @Test
    public void testSequentialRecordsMultipleReturns() {

        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getNextRecord())
                .thenReturn("Record1")
                .thenReturn("Record2")
                .thenReturn("Record3");
        MyService service = new MyService(mockApi);

        String[] records = service.getSequentialRecords();

        assertEquals("Record1", records[0]);
        assertEquals("Record2", records[1]);
        assertEquals("Record3", records[2]);
        verify(mockApi, times(3)).getNextRecord();
    }

    @Test
    public void testMixedDataMultipleReturns() {

        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("DataValue");
        when(mockApi.getNextRecord()).thenReturn("RecordValue");
        MyService service = new MyService(mockApi);

        String result = service.getMixedData();

        assertEquals("DataValue | RecordValue", result);
        verify(mockApi, times(1)).getData();
        verify(mockApi, times(1)).getNextRecord();
    }

    @Test
    public void testMultipleReturnsWithLastException() {

        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getNextRecord())
                .thenReturn("Record1")
                .thenReturn("Record2")
                .thenThrow(new RuntimeException("No more records"));
        MyService service = new MyService(mockApi);

        String[] records = new String[2];
        records[0] = mockApi.getNextRecord();
        records[1] = mockApi.getNextRecord();

        assertEquals("Record1", records[0]);
        assertEquals("Record2", records[1]);

        assertThrows(RuntimeException.class, () -> mockApi.getNextRecord());
    }
}
