package Lambda;

public class Product {
    private String name;
    private String price;
    private String quantity;
    private String total;

    public Product(String name, String price, String quantity, String total) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public Product(String price, String quantity, String total) {
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }
}
