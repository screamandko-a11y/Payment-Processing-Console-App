package org.example.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculator {
    private final double taxRate;

    public TaxCalculator(double taxRate) {
        if (!Double.isFinite(taxRate) || taxRate < 0) {
            throw new IllegalArgumentException("Tax rate must be non-negative and finite.");
        }
        this.taxRate = taxRate;
    }

    public double calculateTax(double amount) {
        if (!Double.isFinite(amount) || amount < 0) {
            throw new IllegalArgumentException("Taxable amount must be non-negative and finite.");
        }

        return BigDecimal.valueOf(amount)
                .multiply(BigDecimal.valueOf(taxRate))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
