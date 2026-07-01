import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testServiceWithMockRepository() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");
        Service service = new Service(mockRepository);

        String result = service.processData();

        assertEquals("Processed Mock Data", result);
        verify(mockRepository, times(1)).getData();
    }

    @Test
    public void testPersistData() {

        Repository mockRepository = mock(Repository.class);
        doNothing().when(mockRepository).saveData(anyString());
        Service service = new Service(mockRepository);

        service.persistData("Test Data");

        verify(mockRepository, times(1)).saveData("Test Data");
    }

    @Test
    public void testRemoveData() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.deleteData("123")).thenReturn(true);
        Service service = new Service(mockRepository);

        boolean result = service.removeData("123");

        assertTrue(result);
        verify(mockRepository, times(1)).deleteData("123");
    }

    @Test
    public void testRemoveDataNotFound() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.deleteData("999")).thenReturn(false);
        Service service = new Service(mockRepository);

        boolean result = service.removeData("999");

        assertFalse(result);
        verify(mockRepository, times(1)).deleteData("999");
    }

    @Test
    public void testFetchAndProcessWithData() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("test data");
        Service service = new Service(mockRepository);

        String result = service.fetchAndProcess();

        assertEquals("Processed TEST DATA", result);
        verify(mockRepository, times(1)).getData();
    }

    @Test
    public void testFetchAndProcessWithNullData() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn(null);
        Service service = new Service(mockRepository);

        String result = service.fetchAndProcess();

        assertEquals("No data to process", result);
        verify(mockRepository, times(1)).getData();
    }

    @Test
    public void testFetchAndProcessWithEmptyData() {

        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("");
        Service service = new Service(mockRepository);

        String result = service.fetchAndProcess();

        assertEquals("No data to process", result);
        verify(mockRepository, times(1)).getData();
    }
}
