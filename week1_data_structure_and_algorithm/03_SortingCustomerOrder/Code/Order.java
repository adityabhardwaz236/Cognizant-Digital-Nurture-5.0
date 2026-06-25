public class Order {
    public int orderId;
    public String customerName;
    public double totalAmount;
    public long orderDate;

    public Order(int orderId, String customerName, double totalAmount, long orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }
}
