import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApiServiceTest {

    @Test
    public void testServiceWithMockRestClient() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");
        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.fetchData();

        assertEquals("Fetched Mock Response", result);
        verify(mockRestClient, times(1)).getResponse();
    }

    @Test
    public void testSubmitDataToApi() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.postData("Test Data")).thenReturn("Success");
        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.submitData("Test Data");

        assertEquals("Submitted Success", result);
        verify(mockRestClient, times(1)).postData("Test Data");
    }

    @Test
    public void testDeleteResourceFromApi() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.deleteResource("123")).thenReturn(true);
        ApiService apiService = new ApiService(mockRestClient);

        boolean result = apiService.removeResource("123");

        assertTrue(result);
        verify(mockRestClient, times(1)).deleteResource("123");
    }

    @Test
    public void testDeleteResourceNotFound() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.deleteResource("999")).thenReturn(false);
        ApiService apiService = new ApiService(mockRestClient);

        boolean result = apiService.removeResource("999");

        assertFalse(result);
        verify(mockRestClient, times(1)).deleteResource("999");
    }

    @Test
    public void testFetchFromEndpointWithData() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getData("/users")).thenReturn("User List Data");
        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.fetchFromEndpoint("/users");

        assertEquals("Retrieved from /users: User List Data", result);
        verify(mockRestClient, times(1)).getData("/users");
    }

    @Test
    public void testFetchFromEndpointNoData() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getData("/empty")).thenReturn("");
        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.fetchFromEndpoint("/empty");

        assertEquals("No data from /empty", result);
        verify(mockRestClient, times(1)).getData("/empty");
    }

    @Test
    public void testGetWeatherData() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getData("/weather")).thenReturn("Sunny, 25C");
        ApiService apiService = new ApiService(mockRestClient);

        String result = apiService.getWeatherData();

        assertEquals("Weather: Sunny, 25C", result);
        verify(mockRestClient, times(1)).getData("/weather");
    }

    @Test
    public void testMultipleApiCalls() {

        RestClient mockRestClient = mock(RestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Response 1");
        when(mockRestClient.postData("Data")).thenReturn("Posted");
        ApiService apiService = new ApiService(mockRestClient);

        String fetchResult = apiService.fetchData();
        String submitResult = apiService.submitData("Data");

        assertEquals("Fetched Response 1", fetchResult);
        assertEquals("Submitted Posted", submitResult);
        verify(mockRestClient, times(1)).getResponse();
        verify(mockRestClient, times(1)).postData("Data");
    }
}
