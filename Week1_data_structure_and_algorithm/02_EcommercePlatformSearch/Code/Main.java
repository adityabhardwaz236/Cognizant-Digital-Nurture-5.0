import java.util.List;

public class Main {
    public static void main(String[] args) {
        SearchEngine platform = new SearchEngine();

        platform.addProduct(1, "iPhone 14", "Electronics", 999, 50);
        platform.addProduct(2, "Samsung Galaxy", "Electronics", 799, 30);
        platform.addProduct(3, "Running Shoes", "Footwear", 120, 100);
        platform.addProduct(4, "Casual Shoes", "Footwear", 80, 75);
        platform.addProduct(5, "Laptop Stand", "Accessories", 45, 200);

        List<Product> results = platform.searchByName("shoes");
        System.out.println("Search by name 'shoes':");
        for (Product p : results) {
            System.out.println("ID: " + p.productId + ", Name: " + p.name + ", Price: $" + p.price);
        }

        results = platform.searchByCategory("Electronics");
        System.out.println("\nSearch by category 'Electronics':");
        for (Product p : results) {
            System.out.println("ID: " + p.productId + ", Name: " + p.name + ", Price: $" + p.price);
        }

        results = platform.searchByPrice(70, 150);
        System.out.println("\nSearch by price range $70-$150:");
        for (Product p : results) {
            System.out.println("ID: " + p.productId + ", Name: " + p.name + ", Price: $" + p.price);
        }
    }
}
