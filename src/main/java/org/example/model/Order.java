package org.example.model;

import org.example.service.TaxCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private final String customerName;
    private final List<OrderItem> items;
    private final TaxCalculator taxCalculator;
    private OrderStatus status;
    private Discount discount = new NoDiscount();

    public Order(Builder builder) {
        this.customerName = builder.customerName;
        this.items = builder.items;
        this.taxCalculator = Objects.requireNonNull(
                builder.taxCalculator, "Tax calculator is required."
        );
        this.status = OrderStatus.NEW;
    }

    public void addItem(OrderItem item){
        // prevented adding items if order is already

        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Cannot add items. Order is already completed!");
        }
        items.add(item);
    }

    public double calculateSubtotal(){

        double subTotal = 0;

        // calculate 'total' using instance every 'order Item'
        for (OrderItem item : items) {
            subTotal += item.calculateTotal();
        }

        return subTotal;
    }

    private double calculateDiscountedTotal() {
        return discount.apply(calculateSubtotal());
    }

    public double calculateTax() {
        return taxCalculator.calculateTax(calculateDiscountedTotal());
    }

    public double calculateTotal() {
        double discountedTotal = calculateDiscountedTotal();
        double tax = taxCalculator.calculateTax(discountedTotal);

        return BigDecimal.valueOf(discountedTotal)
                .add(BigDecimal.valueOf(tax))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public void markAsPaid(){
        //validated order is not empty

        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot pay for an empty order!");
        }

        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Order cannot be paid in its current status!");
        }
        this.status = OrderStatus.PAID;
    }

    public void applyDiscount(Discount discount){
        this.discount = discount;
    }

    public boolean isPaid(){
        return this.status == OrderStatus.PAID;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    public String getCustomerName() {
        return customerName;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private String customerName;
        private List<OrderItem> items = new ArrayList<>();
        private TaxCalculator taxCalculator;

        public Builder taxCalculator(TaxCalculator taxCalculator) {
            this.taxCalculator = taxCalculator;
            return this;
        }

        public Builder customerName(String customerName){
            this.customerName = customerName;
            return this;
        }
        public Builder addItem(OrderItem item){
            this.items.add(item);
            return this;
        }
        public Order build(){
            // TODO: validate customerName
            return new Order(this);
        }
    }
}
