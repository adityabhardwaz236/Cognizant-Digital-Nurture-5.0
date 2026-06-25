import java.util.ArrayList;
import java.util.List;

public class ForecastingEngine {
    private List<MonthlyData> data;

    public ForecastingEngine() {
        this.data = new ArrayList<>();
    }

    public void addMonth(int month, double revenue, double expense) {
        data.add(new MonthlyData(month, revenue, expense));
    }

    public double getTotalRevenue() {
        double total = 0;
        for (MonthlyData md : data) {
            total += md.revenue;
        }
        return total;
    }

    public double getTotalExpense() {
        double total = 0;
        for (MonthlyData md : data) {
            total += md.expense;
        }
        return total;
    }

    public double getNetProfit() {
        return getTotalRevenue() - getTotalExpense();
    }

    public double getProfitMargin() {
        double totalRevenue = getTotalRevenue();
        if (totalRevenue == 0) return 0;
        return (getNetProfit() / totalRevenue) * 100;
    }

    public double getAverageRevenue() {
        if (data.isEmpty()) return 0;
        return getTotalRevenue() / data.size();
    }

    public void displayReport() {
        System.out.println("Month | Revenue | Expense | Profit");
        for (MonthlyData md : data) {
            System.out.println(md.month + " | $" + md.revenue + " | $" + md.expense + 
                             " | $" + md.getProfit());
        }
    }
}
