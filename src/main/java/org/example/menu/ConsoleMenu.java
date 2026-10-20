package org.example.menu;

import org.example.config.AppConfig;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.PaymentResult;
import org.example.payment.PaymentMethod;
import org.example.payment.PaymentProcessor;
import org.example.service.OrderService;

import java.util.Scanner;

public class ConsoleMenu {
    private final ConsoleInput input;
    private final PaymentMethodReader paymentMethodReader;
    private final OrderService orderService;

    public ConsoleMenu() {
        this(
                new ConsoleInput(new Scanner(System.in)),
                new OrderService(new PaymentProcessor())
        );
    }

    public ConsoleMenu(ConsoleInput input, OrderService orderService) {
        this.input = input;
        this.paymentMethodReader = new PaymentMethodReader(input);
        this.orderService = orderService;
    }

    public void start() {
        AppConfig config = AppConfig.getInstance();
        System.out.println(
                "Welcome to " + config.getApplicationName()
        );

        boolean running = true;

        while (running) {
            printMenu();

            int option = input.readInt("Choose an option:");

            try {
                switch (option) {
                    case 1 -> createOrder();
                    case 2 -> addItem();
                    case 3 -> viewOrder();
                    case 4 -> payOrder();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (IllegalStateException | IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void createOrder() {
        String customerName = input.readString("Customer name:");

        orderService.createOrder(customerName);

        System.out.println("Order created for " + customerName);
    }

    private void addItem() {
        orderService.getCurrentOrder();

        String itemName = input.readString("Item name:");
        double price = input.readNonNegativeDouble("Price:");
        int quantity = input.readPositiveInt("Quantity:");

        orderService.addItem(itemName, price, quantity);

        System.out.println("Item added to order.");
    }

    private void viewOrder() {
        Order currentOrder = orderService.getCurrentOrder();

        System.out.println(
                "Customer: " + currentOrder.getCustomerName()
        );
        System.out.println("Status: " + currentOrder.getStatus());
        System.out.println("Items:");

        for (OrderItem item : currentOrder.getItems()) {
            System.out.println("- " + item);
        }

        System.out.println(
                "Total: " + currentOrder.calculateTotal()
        );
    }

    private void payOrder() {
        orderService.getCurrentOrder();

        PaymentMethod paymentMethod =
                paymentMethodReader.readPaymentMethod();

        PaymentResult result = orderService.payOrder(paymentMethod);
        System.out.println(result.getMessage());
    }

    private void printMenu() {
        System.out.println("""
                  1. Create order
                  2. Add item to order
                  3. View order
                  4. Pay order
                  0. Exit
                  """);
    }
}
