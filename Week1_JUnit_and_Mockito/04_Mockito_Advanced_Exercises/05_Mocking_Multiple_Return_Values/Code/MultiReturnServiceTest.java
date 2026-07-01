import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MultiReturnServiceTest {

    @Test
    public void testServiceWithMultipleReturnValues() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("First Mock Data")
                .thenReturn("Second Mock Data");
        Service service = new Service(mockRepository);

        String firstResult = service.processData();
        String secondResult = service.processData();

        assertEquals("Processed First Mock Data", firstResult);
        assertEquals("Processed Second Mock Data", secondResult);
        verify(mockRepository, times(2)).getData();
    }

    @Test
    public void testProcessMultipleValuesSequentially() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("Value1")
                .thenReturn("Value2")
                .thenReturn("Value3");
        Service service = new Service(mockRepository);

        String[] results = service.processMultipleValues();

        assertEquals("Processed Value1", results[0]);
        assertEquals("Processed Value2", results[1]);
        assertEquals("Processed Value3", results[2]);
        verify(mockRepository, times(3)).getData();
    }

    @Test
    public void testGetSequentialData() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getNextValue()).thenReturn("Next1");
        Service service = new Service(mockRepository);

        String result = service.getSequentialData();

        assertEquals("Next1", result);
        verify(mockRepository, times(1)).getNextValue();
    }

    @Test
    public void testGetThreeSequentialValues() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getNextValue())
                .thenReturn("First")
                .thenReturn("Second")
                .thenReturn("Third");
        Service service = new Service(mockRepository);

        String[] values = service.getThreeSequentialValues();

        assertEquals("First", values[0]);
        assertEquals("Second", values[1]);
        assertEquals("Third", values[2]);
        verify(mockRepository, times(3)).getNextValue();
    }

    @Test
    public void testInterleaveMultipleSources() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("DataValue");
        when(mockRepository.getNextValue()).thenReturn("NextValue");
        when(mockRepository.fetchRecord()).thenReturn("RecordValue");
        Service service = new Service(mockRepository);

        String[] results = service.interleaveMultipleSources();

        assertEquals("DataValue", results[0]);
        assertEquals("NextValue", results[1]);
        assertEquals("RecordValue", results[2]);
        verify(mockRepository, times(1)).getData();
        verify(mockRepository, times(1)).getNextValue();
        verify(mockRepository, times(1)).fetchRecord();
    }

    @Test
    public void testMultipleCallsWithDifferentValues() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("Data1")
                .thenReturn("Data2")
                .thenReturn("Data3")
                .thenReturn("Data4")
                .thenReturn("Data5");
        Service service = new Service(mockRepository);

        String result1 = service.processData();
        String result2 = service.processData();
        String result3 = service.processData();
        String result4 = service.processData();
        String result5 = service.processData();

        assertEquals("Processed Data1", result1);
        assertEquals("Processed Data2", result2);
        assertEquals("Processed Data3", result3);
        assertEquals("Processed Data4", result4);
        assertEquals("Processed Data5", result5);
        verify(mockRepository, times(5)).getData();
    }

    @Test
    public void testSequentialThenReturnException() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("Success")
                .thenThrow(new RuntimeException("Error"));
        Service service = new Service(mockRepository);

        String result = service.processData();
        assertEquals("Processed Success", result);

        assertThrows(RuntimeException.class, () -> service.processData());
        verify(mockRepository, times(2)).getData();
    }

    @Test
    public void testComplexScenarioMultipleReturnsAndMethods() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData())
                .thenReturn("Data1")
                .thenReturn("Data2");
        when(mockRepository.getNextValue())
                .thenReturn("Next1")
                .thenReturn("Next2")
                .thenReturn("Next3");
        Service service = new Service(mockRepository);

        String[] sequentialValues = service.getThreeSequentialValues();
        String firstData = service.processData();
        String secondData = service.processData();

        assertEquals("Next1", sequentialValues[0]);
        assertEquals("Next2", sequentialValues[1]);
        assertEquals("Next3", sequentialValues[2]);
        assertEquals("Processed Data1", firstData);
        assertEquals("Processed Data2", secondData);
        verify(mockRepository, times(3)).getNextValue();
        verify(mockRepository, times(2)).getData();
    }
}
