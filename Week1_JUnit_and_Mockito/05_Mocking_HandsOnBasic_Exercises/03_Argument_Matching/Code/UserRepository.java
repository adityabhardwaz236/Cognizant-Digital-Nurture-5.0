public interface UserRepository {
    void saveUser(String userId, String userName);
    String getUserName(String userId);
    void deleteUser(String userId);
    boolean userExists(String userId);
}
