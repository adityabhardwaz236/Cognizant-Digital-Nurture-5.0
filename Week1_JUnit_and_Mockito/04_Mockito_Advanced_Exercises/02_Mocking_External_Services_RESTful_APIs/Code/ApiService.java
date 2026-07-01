public class ApiService {
    private RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        String response = restClient.getResponse();
        return "Fetched " + response;
    }

    public String submitData(String data) {
        String response = restClient.postData(data);
        return "Submitted " + response;
    }

    public boolean removeResource(String id) {
        return restClient.deleteResource(id);
    }

    public String fetchFromEndpoint(String endpoint) {
        String data = restClient.getData(endpoint);
        if (data != null && !data.isEmpty()) {
            return "Retrieved from " + endpoint + ": " + data;
        }
        return "No data from " + endpoint;
    }

    public String getWeatherData() {
        String response = restClient.getData("/weather");
        return "Weather: " + response;
    }
}
