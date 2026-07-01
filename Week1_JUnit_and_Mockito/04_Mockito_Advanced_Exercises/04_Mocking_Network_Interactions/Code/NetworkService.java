public class NetworkService {
    private NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String connection = networkClient.connect();
        return "Connected to " + connection;
    }

    public String sendData(String data) {
        String response = networkClient.sendMessage(data);
        return "Response: " + response;
    }

    public boolean closeConnection() {
        return networkClient.disconnect();
    }

    public int checkConnectionStatus() {
        return networkClient.getConnectionStatus();
    }

    public String establishAndSend(String message) {
        String connection = networkClient.connect();
        if (connection != null && !connection.isEmpty()) {
            String response = networkClient.sendMessage(message);
            return "Sent to " + connection + ": " + response;
        }
        return "Connection failed";
    }

    public boolean isConnected() {
        return networkClient.getConnectionStatus() == 1;
    }
}
