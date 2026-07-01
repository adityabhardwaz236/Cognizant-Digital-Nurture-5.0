public interface Database {
    String fetchData(String query);
    void saveData(String data);
}

public interface Cache {
    void store(String key, String value);
    String retrieve(String key);
}
