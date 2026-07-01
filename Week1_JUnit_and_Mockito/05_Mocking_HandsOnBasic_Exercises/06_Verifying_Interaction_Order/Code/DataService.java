public class DataService {
    private Database database;
    private Cache cache;

    public DataService(Database database, Cache cache) {
        this.database = database;
        this.cache = cache;
    }

    public String retrieveData(String query) {

        String cachedData = cache.retrieve("query_" + query);
        if (cachedData != null) {
            return cachedData;
        }

        String data = database.fetchData(query);

        cache.store("query_" + query, data);

        return data;
    }

    public void updateDataFlow(String newData) {
        database.saveData(newData);
        cache.store("latest", newData);
    }
}
