import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MyServiceTest {

    @Test
    public void testVerifyInteraction() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Test Data");
        MyService service = new MyService(mockApi);

        service.fetchData();

        verify(mockApi).getData();
    }

    @Test
    public void testVerifyUpdateDataCalled() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        service.updateData("New Data");

        verify(mockApi).updateData("New Data");
    }

    @Test
    public void testVerifyFetchRecordCalled() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.fetchRecord("123")).thenReturn("Record 123");
        MyService service = new MyService(mockApi);

        service.getRecord("123");

        verify(mockApi).fetchRecord("123");
    }

    @Test
    public void testVerifyCallCount() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Data");
        MyService service = new MyService(mockApi);

        service.fetchData();
        service.fetchData();

        verify(mockApi, times(2)).getData();
    }

    @Test
    public void testVerifyMultipleInteractions() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Data");
        MyService service = new MyService(mockApi);

        service.processAndUpdate("Updated");

        verify(mockApi).getData();
        verify(mockApi).updateData("Updated");
    }

    @Test
    public void testVerifyNotCalled() {

        ExternalApi mockApi = Mockito.mock(ExternalApi.class);
        MyService service = new MyService(mockApi);

        verify(mockApi, never()).updateData(anyString());
    }
}
