import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkServiceTest {

    @Test
    public void testServiceWithMockNetworkClient() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");
        NetworkService networkService = new NetworkService(mockNetworkClient);

        String result = networkService.connectToServer();

        assertEquals("Connected to Mock Connection", result);
        verify(mockNetworkClient, times(1)).connect();
    }

    @Test
    public void testSendDataOverNetwork() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.sendMessage("Hello")).thenReturn("Hello Received");
        NetworkService networkService = new NetworkService(mockNetworkClient);

        String result = networkService.sendData("Hello");

        assertEquals("Response: Hello Received", result);
        verify(mockNetworkClient, times(1)).sendMessage("Hello");
    }

    @Test
    public void testCloseConnection() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.disconnect()).thenReturn(true);
        NetworkService networkService = new NetworkService(mockNetworkClient);

        boolean result = networkService.closeConnection();

        assertTrue(result);
        verify(mockNetworkClient, times(1)).disconnect();
    }

    @Test
    public void testConnectionStatusConnected() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.getConnectionStatus()).thenReturn(1);
        NetworkService networkService = new NetworkService(mockNetworkClient);

        int status = networkService.checkConnectionStatus();

        assertEquals(1, status);
        verify(mockNetworkClient, times(1)).getConnectionStatus();
    }

    @Test
    public void testConnectionStatusDisconnected() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.getConnectionStatus()).thenReturn(0);
        NetworkService networkService = new NetworkService(mockNetworkClient);

        int status = networkService.checkConnectionStatus();

        assertEquals(0, status);
        verify(mockNetworkClient, times(1)).getConnectionStatus();
    }

    @Test
    public void testEstablishAndSendMessage() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Server.com");
        when(mockNetworkClient.sendMessage("Test Message")).thenReturn("Acknowledged");
        NetworkService networkService = new NetworkService(mockNetworkClient);

        String result = networkService.establishAndSend("Test Message");

        assertEquals("Sent to Server.com: Acknowledged", result);
        verify(mockNetworkClient, times(1)).connect();
        verify(mockNetworkClient, times(1)).sendMessage("Test Message");
    }

    @Test
    public void testIsConnectedTrue() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.getConnectionStatus()).thenReturn(1);
        NetworkService networkService = new NetworkService(mockNetworkClient);

        boolean isConnected = networkService.isConnected();

        assertTrue(isConnected);
        verify(mockNetworkClient, times(1)).getConnectionStatus();
    }

    @Test
    public void testIsConnectedFalse() {

        NetworkClient mockNetworkClient = mock(NetworkClient.class);
        when(mockNetworkClient.getConnectionStatus()).thenReturn(0);
        NetworkService networkService = new NetworkService(mockNetworkClient);

        boolean isConnected = networkService.isConnected();

        assertFalse(isConnected);
        verify(mockNetworkClient, times(1)).getConnectionStatus();
    }
}
