package org.example.payment;

import org.example.model.PaymentResult;

public class GiftCardPayment extends PaymentMethod {
    private final String code;
    private double balance;

    public GiftCardPayment(String providerName, String code, double balance) {
        super(providerName);
        this.code = code;
        this.balance = balance;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        if (amount > balance) {
            return new PaymentResult(
                    false,
                    "Insufficient balance. Available: " + balance
            );
        }
        balance -= amount;

        return new PaymentResult(
                true,
                "Paid " + amount
                        + " using gift card. Remaining balance: " + balance
        );
    }
}
