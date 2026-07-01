public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String data = repository.getData();
        return "Processed " + data;
    }

    public void persistData(String data) {
        repository.saveData(data);
    }

    public boolean removeData(String id) {
        return repository.deleteData(id);
    }

    public String fetchAndProcess() {
        String rawData = repository.getData();
        if (rawData != null && !rawData.isEmpty()) {
            return "Processed " + rawData.toUpperCase();
        }
        return "No data to process";
    }
}
