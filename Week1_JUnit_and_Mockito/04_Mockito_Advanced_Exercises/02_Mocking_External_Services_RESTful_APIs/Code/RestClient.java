public interface RestClient {
    String getResponse();
    String postData(String data);
    boolean deleteResource(String id);
    String getData(String endpoint);
}
