package org.example.payment;

import org.example.model.Order;
import org.example.model.OrderStatus;
import org.example.model.PaymentResult;

public class PaymentProcessor {
    public PaymentResult process(Order order, PaymentMethod paymentMethod){

        // prevent paying already paid or cancelled orders
        if (order.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException(
                    "Order cannot be paid in its current status!"
            );
        }

        // prevent paying empty orders
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException(
                    "Cannot pay for an empty order!"
            );
        }

        PaymentResult result = paymentMethod.pay(order.calculateTotal());

        if(result.isSuccessful()){
            order.markAsPaid();
        }
        return result;
    }
}
