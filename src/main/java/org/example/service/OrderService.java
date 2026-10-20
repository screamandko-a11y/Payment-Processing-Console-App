package org.example.service;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.PaymentResult;
import org.example.payment.PaymentMethod;
import org.example.payment.PaymentProcessor;

public class OrderService {
    private final PaymentProcessor paymentProcessor;
    private final TaxCalculator taxCalculator;
    private Order currentOrder;

    public OrderService(PaymentProcessor paymentProcessor, TaxCalculator taxCalculator) {
        this.paymentProcessor = paymentProcessor;
        this.taxCalculator = taxCalculator;
    }

    public void createOrder(String customerName) {
        currentOrder = Order.builder()
                .customerName(customerName)
                .taxCalculator(taxCalculator)
                .build();
    }

    public Order getCurrentOrder() {
        if (currentOrder == null) {
            throw new IllegalStateException(
                    "No order exists. Please create an order first!"
            );
        }
        return currentOrder;
    }

    public void addItem(String name, double price, int quantity) {
        getCurrentOrder().addItem(new OrderItem(name, price, quantity));
    }

    public PaymentResult payOrder(PaymentMethod paymentMethod) {
        return paymentProcessor.process(getCurrentOrder(), paymentMethod);
    }
}
