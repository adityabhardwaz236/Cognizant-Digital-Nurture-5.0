public class MonthlyData {
    public int month;
    public double revenue;
    public double expense;

    public MonthlyData(int month, double revenue, double expense) {
        this.month = month;
        this.revenue = revenue;
        this.expense = expense;
    }

    public double getProfit() {
        return revenue - expense;
    }
}
