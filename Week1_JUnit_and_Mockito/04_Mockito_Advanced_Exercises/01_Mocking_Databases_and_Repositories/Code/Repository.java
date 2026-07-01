public interface Repository {
    String getData();
    void saveData(String data);
    boolean deleteData(String id);
}
