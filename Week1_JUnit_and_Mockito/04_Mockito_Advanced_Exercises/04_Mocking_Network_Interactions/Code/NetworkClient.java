public interface NetworkClient {
    String connect();
    String sendMessage(String message);
    boolean disconnect();
    int getConnectionStatus();
}
