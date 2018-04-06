public class Item {
    private String name;
    private int price;
    private boolean bogof;

    public Item(String name, int price, boolean bogof) {
        this.name = name;
        this.price = price;
        this.bogof = bogof;

    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean isBogof() {
        return this.bogof;
    }

}
