package org.example.model;

public class NoDiscount extends Discount {
    public NoDiscount() {
        super("NONE");
    }

    // return originalAmount without discount
    @Override
    public double apply(double originalAmount) {
        return originalAmount;
    }
}
