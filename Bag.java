public class Bag {
    private String color;
    private String size;
    private float price;

    public Bag(String color, String size, float price) {
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public void show() {
        System.out.printf("Color: %s, Size: %s, Cost: $%.2f%n", color, size, price);
    }

    public static void main(String[] args) {
        Bag myBag = new Bag("Red", "Medium", 100);
        myBag.show();
    }
}


