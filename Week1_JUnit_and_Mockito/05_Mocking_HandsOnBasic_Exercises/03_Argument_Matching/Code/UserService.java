public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String userName) {
        repository.saveUser(userId, userName);
    }

    public String getUserName(String userId) {
        return repository.getUserName(userId);
    }

    public void removeUser(String userId) {
        repository.deleteUser(userId);
    }

    public boolean checkUserExists(String userId) {
        return repository.userExists(userId);
    }
}
