package org.example.model;

public class FixedAmountDiscount extends Discount {
    private final double amount;

    public FixedAmountDiscount(String code, double amount) {
        super(code);

        if (amount < 0) {
            throw new IllegalArgumentException(
                    "Discount must not be negative."
            );
        }

        this.amount = amount;
    }

    @Override
    public double apply(double originalAmount) {
        return Math.max(0, originalAmount - amount);
    }

//    private double applyDiscount(double total) {
//        double discountPercent;
//
//        if (total > 800) {
//            discountPercent = 80;
//        } else if (total > 700) {
//            discountPercent = 70;
//        } else if (total > 600) {
//            discountPercent = 60;
//        } else if (total > 500) {
//            discountPercent = 50;
//        } else if (total > 400) {
//            discountPercent = 40;
//        } else if (total > 300) {
//            discountPercent = 30;
//        } else if (total > 200) {
//            discountPercent = 20;
//        } else if (total > 100) {
//            discountPercent = 10;
//        } else {
//            discountPercent = 0;
//        }
//
//        double discountAmount = total * discountPercent / 100.0;
//
//        Discount discount = new FixedAmountDiscount(
//                "AUTO" + (int) discountPercent,
//                discountAmount
//        );
//
//        return discount.apply(total);
//    }
}