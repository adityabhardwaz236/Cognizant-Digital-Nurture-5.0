public class SimpleCalculator {

    private int result = 0;

    public void add(int value) {
        result += value;
    }

    public void subtract(int value) {
        result -= value;
    }

    public int getResult() {
        return result;
    }

    public void reset() {
        result = 0;
    }
}
