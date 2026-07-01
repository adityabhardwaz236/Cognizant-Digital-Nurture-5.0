public class MyService {
    private ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    public String fetchDataMultipleTimes() {
        String data1 = externalApi.getData();
        String data2 = externalApi.getData();
        String data3 = externalApi.getData();
        return data1 + ", " + data2 + ", " + data3;
    }

    public String[] getSequentialRecords() {
        String[] records = new String[3];
        records[0] = externalApi.getNextRecord();
        records[1] = externalApi.getNextRecord();
        records[2] = externalApi.getNextRecord();
        return records;
    }

    public String getMixedData() {
        String data = externalApi.getData();
        String record = externalApi.getNextRecord();
        return data + " | " + record;
    }
}
