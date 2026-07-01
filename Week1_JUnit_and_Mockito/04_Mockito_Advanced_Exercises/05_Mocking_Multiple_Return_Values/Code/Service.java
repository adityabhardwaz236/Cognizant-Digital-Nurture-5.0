public class Service {
    private Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String data = repository.getData();
        return "Processed " + data;
    }

    public String[] processMultipleValues() {
        String[] results = new String[3];
        results[0] = processData();
        results[1] = processData();
        results[2] = processData();
        return results;
    }

    public String getSequentialData() {
        return repository.getNextValue();
    }

    public String[] getThreeSequentialValues() {
        String[] values = new String[3];
        values[0] = repository.getNextValue();
        values[1] = repository.getNextValue();
        values[2] = repository.getNextValue();
        return values;
    }

    public String[] interleaveMultipleSources() {
        String[] results = new String[3];
        results[0] = repository.getData();
        results[1] = repository.getNextValue();
        results[2] = repository.fetchRecord();
        return results;
    }
}
