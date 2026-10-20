package org.example.payment;

import org.example.model.PaymentResult;

/**
 * Simulates a cryptocurrency payment without sending a blockchain transaction.
 */
public class CryptoPayment extends PaymentMethod {
    private final String cryptocurrency;
    private final String walletAddress;

    public CryptoPayment(String cryptocurrency, String walletAddress) {
        super("Crypto");
        this.cryptocurrency = cryptocurrency;
        this.walletAddress = walletAddress;
    }

    @Override
    protected PaymentResult processPayment(double amount) {
        if (cryptocurrency == null || cryptocurrency.isBlank()) {
            return new PaymentResult(false, "Cryptocurrency is required.");
        }

        if (walletAddress == null || walletAddress.isBlank()) {
            return new PaymentResult(false, "Wallet address is required.");
        }

        // Amount remains the order total; no exchange rate is applied.
        return new PaymentResult(
                true,
                "Simulated crypto payment using " + cryptocurrency
                        + ". Order amount: " + amount
        );
    }
}
