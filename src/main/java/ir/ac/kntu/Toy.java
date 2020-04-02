package ir.ac.kntu;

public class Toy {
    private double basePrice;
    protected ToySize size;

    public Toy(final int basePrice, final ToySize size) {
        this.basePrice = basePrice;
        this.size = size;
    }

    public double getPrice() {
        //TODO
    }

    public double getPrice(double discount) {
        //TODO
    }

    public double getBasePrice() {
        return basePrice;
    }
}