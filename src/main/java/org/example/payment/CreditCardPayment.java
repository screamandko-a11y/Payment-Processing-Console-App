package org.example.payment;

import org.example.model.PaymentResult;

public class CreditCardPayment extends PaymentMethod {
    private final String cardNumber;
    private final String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        super("CreditCard");
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        // validate payment amount
        if (!Double.isFinite(amount) || amount <= 0) {
            return new PaymentResult(false, "Invalid payment amount");
        }

        // validate card number
        if (cardNumber == null || cardNumber.isBlank()) {
            return new PaymentResult(false, "Card number is required");
        }

        // validate card number length
        if (cardNumber.length() < 16) {
            return new PaymentResult(false, "Invalid card number");
        }

        if (cardHolderName == null || cardHolderName.isBlank()) {
            return new PaymentResult(false, "Card holder name is required");
        }

        return new PaymentResult(
                true,
                "Paid " + amount + " using credit card ending with "
                        + cardNumber.substring(cardNumber.length() - 4)
        );
    }
}
