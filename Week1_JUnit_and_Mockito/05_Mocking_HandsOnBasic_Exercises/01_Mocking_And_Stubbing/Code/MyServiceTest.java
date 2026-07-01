import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class MyServiceTest {

    @Test
    public void testExternalApi() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);

        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    @Test
    public void testFetchUserInfo() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.fetchUserInfo("user123")).thenReturn("John Doe");
        MyService service = new MyService(mockApi);

        String result = service.getUserInfo("user123");

        assertEquals("John Doe", result);
    }

    @Test
    public void testProcessApiData() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Raw Data");
        MyService service = new MyService(mockApi);

        String result = service.processApiData();

        assertEquals("Processed: Raw Data", result);
    }

    @Test
    public void testGetTotalCount() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getCount()).thenReturn(42);
        MyService service = new MyService(mockApi);

        int result = service.getTotalCount();

        assertEquals(42, result);
    }

    @Test
    public void testMultipleStubbedMethods() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Data");
        when(mockApi.getCount()).thenReturn(10);
        MyService service = new MyService(mockApi);

        String data = service.fetchData();
        int count = service.getTotalCount();

        assertEquals("Data", data);
        assertEquals(10, count);
    }
}
