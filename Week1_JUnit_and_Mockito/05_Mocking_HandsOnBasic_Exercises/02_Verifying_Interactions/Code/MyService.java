public class MyService {
    private ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchData() {
        return externalApi.getData();
    }

    public void updateData(String data) {
        externalApi.updateData(data);
    }

    public String getRecord(String id) {
        return externalApi.fetchRecord(id);
    }

    public void processAndUpdate(String newData) {
        String data = externalApi.getData();
        externalApi.updateData(newData);
    }
}
