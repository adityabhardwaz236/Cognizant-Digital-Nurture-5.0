public class PerformanceTester {

    public void performQuickTask() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void performTask() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void performSlowTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int calculateComplexNumber() {
        int result = 0;
        for (int i = 0; i < 1000000; i++) {
            result += i * i;
        }
        return result;
    }
}
