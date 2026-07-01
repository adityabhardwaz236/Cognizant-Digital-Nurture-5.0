public class ExceptionThrower {

    public void throwException() {
        throw new IllegalArgumentException("This is a test exception");
    }

    public void throwExceptionIfNull(String value) {
        if (value == null) {
            throw new NullPointerException("Value cannot be null");
        }
    }

    public void validateAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
    }
}
