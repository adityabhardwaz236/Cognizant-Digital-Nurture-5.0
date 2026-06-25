import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Item> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(int itemId, String name, int quantity, double price) {
        if (items.containsKey(itemId)) {
            items.get(itemId).quantity += quantity;
        } else {
            items.put(itemId, new Item(itemId, name, quantity, price));
        }
    }

    public boolean removeItem(int itemId, int qty) {
        if (items.containsKey(itemId)) {
            Item item = items.get(itemId);
            if (item.quantity >= qty) {
                item.quantity -= qty;
                if (item.quantity == 0) {
                    items.remove(itemId);
                }
                return true;
            }
        }
        return false;
    }

    public void updatePrice(int itemId, double newPrice) {
        if (items.containsKey(itemId)) {
            items.get(itemId).price = newPrice;
        }
    }

    public double getTotalValue() {
        double total = 0;
        for (Item item : items.values()) {
            total += item.getTotalValue();
        }
        return total;
    }

    public Item getItem(int itemId) {
        return items.get(itemId);
    }

    public void displayAll() {
        for (Item item : items.values()) {
            System.out.println("ID: " + item.itemId + ", Name: " + item.name + 
                             ", Qty: " + item.quantity + ", Price: $" + item.price);
        }
    }
}
