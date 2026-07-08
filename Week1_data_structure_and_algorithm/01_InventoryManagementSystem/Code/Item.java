public class Item {
    public int itemId;
    public String name;
    public int quantity;
    public double price;

    public Item(int itemId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalValue() {
        return quantity * price;
    }
}
