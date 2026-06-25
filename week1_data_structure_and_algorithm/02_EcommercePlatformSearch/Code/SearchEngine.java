import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private List<Product> products;

    public SearchEngine() {
        this.products = new ArrayList<>();
    }

    public void addProduct(int id, String name, String category, double price, int stock) {
        products.add(new Product(id, name, category, price, stock));
    }

    public List<Product> searchByName(String keyword) {
        List<Product> results = new ArrayList<>();
        String lower = keyword.toLowerCase();
        for (Product p : products) {
            if (p.name.toLowerCase().contains(lower)) {
                results.add(p);
            }
        }
        return results;
    }

    public List<Product> searchByCategory(String category) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.category.equalsIgnoreCase(category)) {
                results.add(p);
            }
        }
        return results;
    }

    public List<Product> searchByPrice(double min, double max) {
        List<Product> results = new ArrayList<>();
        for (Product p : products) {
            if (p.price >= min && p.price <= max) {
                results.add(p);
            }
        }
        return results;
    }
}
