public class Main {
    public static void main(String[] args) {
        OrderManager orderSystem = new OrderManager();

        orderSystem.addOrder(101, "Alice", 250.50, 1624838400L);
        orderSystem.addOrder(102, "Bob", 450.75, 1624924800L);
        orderSystem.addOrder(103, "Charlie", 150.25, 1624752000L);
        orderSystem.addOrder(104, "Diana", 320.00, 1625011200L);

        System.out.println("Orders sorted by Amount (Descending):");
        orderSystem.sortByAmount();
        orderSystem.display();

        System.out.println("\nOrders sorted by Customer Name:");
        orderSystem.sortByName();
        orderSystem.display();

        System.out.println("\nOrders sorted by Date:");
        orderSystem.sortByDate();
        orderSystem.display();
    }
}
