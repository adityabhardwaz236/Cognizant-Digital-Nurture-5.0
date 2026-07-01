public class MyService {
    private ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public String getUserInfo(String userId) {
        return externalApi.fetchUserInfo(userId);
    }

    public String processApiData() {
        String data = externalApi.getData();
        return "Processed: " + data;
    }

    public int getTotalCount() {
        return externalApi.getCount();
    }
}
