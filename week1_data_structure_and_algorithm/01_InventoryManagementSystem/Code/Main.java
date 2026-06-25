public class Main {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        inventory.addItem(1, "Laptop", 5, 1000);
        inventory.addItem(2, "Mouse", 20, 25);
        inventory.addItem(3, "Keyboard", 15, 75);

        System.out.println("Inventory Items:");
        inventory.displayAll();

        System.out.println("\nTotal Value: $" + inventory.getTotalValue());

        inventory.removeItem(2, 5);
        System.out.println("\nAfter removing 5 mice:");
        Item mouse = inventory.getItem(2);
        System.out.println("Mouse quantity: " + (mouse != null ? mouse.quantity : "Out of stock"));

        inventory.updatePrice(1, 950);
        System.out.println("\nAfter price update:");
        System.out.println("Total Value: $" + inventory.getTotalValue());
    }
}
