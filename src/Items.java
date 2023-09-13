public class Items {
    Items() {}
    Items(double quantity) {
        this.quantity = quantity;
    }
    Items(String name, double quantity, double price, String date) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String name;
    private double quantity;
    private double price;
    private String date;
}
