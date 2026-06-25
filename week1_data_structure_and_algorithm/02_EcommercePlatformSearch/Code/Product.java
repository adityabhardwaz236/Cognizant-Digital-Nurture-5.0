public class Product {
    public int productId;
    public String name;
    public String category;
    public double price;
    public int stock;

    public Product(int productId, String name, String category, double price, int stock) {
        this.productId = productId;
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
}
