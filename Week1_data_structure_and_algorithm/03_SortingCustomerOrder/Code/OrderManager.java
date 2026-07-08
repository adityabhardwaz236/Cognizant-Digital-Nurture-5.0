import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderManager {
    private List<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(int id, String name, double amount, long date) {
        orders.add(new Order(id, name, amount, date));
    }

    public void sortByAmount() {
        Collections.sort(orders, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Double.compare(o2.totalAmount, o1.totalAmount);
            }
        });
    }

    public void sortByName() {
        Collections.sort(orders, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return o1.customerName.compareTo(o2.customerName);
            }
        });
    }

    public void sortByDate() {
        Collections.sort(orders, new Comparator<Order>() {
            public int compare(Order o1, Order o2) {
                return Long.compare(o1.orderDate, o2.orderDate);
            }
        });
    }

    public void display() {
        for (Order o : orders) {
            System.out.println("ID: " + o.orderId + ", Customer: " + o.customerName + 
                             ", Amount: $" + o.totalAmount + ", Date: " + o.orderDate);
        }
    }
}
