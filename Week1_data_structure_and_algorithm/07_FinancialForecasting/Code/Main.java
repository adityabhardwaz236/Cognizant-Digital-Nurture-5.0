public class Main {
    public static void main(String[] args) {
        ForecastingEngine forecast = new ForecastingEngine();

        forecast.addMonth(1, 50000, 30000);
        forecast.addMonth(2, 55000, 32000);
        forecast.addMonth(3, 60000, 35000);
        forecast.addMonth(4, 58000, 33000);
        forecast.addMonth(5, 65000, 37000);
        forecast.addMonth(6, 70000, 40000);

        System.out.println("Monthly Financial Report:");
        forecast.displayReport();

        System.out.println("\nTotal Revenue: $" + forecast.getTotalRevenue());
        System.out.println("Total Expense: $" + forecast.getTotalExpense());
        System.out.println("Net Profit: $" + forecast.getNetProfit());

        System.out.println("\nAverage Monthly Revenue: $" + forecast.getAverageRevenue());
        System.out.println("Profit Margin: " + String.format("%.2f", forecast.getProfitMargin()) + "%");
    }
}
