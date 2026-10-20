package org.example;

import org.example.config.AppConfig;
import org.example.menu.ConsoleInput;
import org.example.menu.ConsoleMenu;
import org.example.payment.PaymentProcessor;
import org.example.service.OrderService;
import org.example.service.TaxCalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppConfig config = AppConfig.getInstance();
        TaxCalculator taxCalculator = new TaxCalculator(config.getTaxRate());
        OrderService orderService = new OrderService(
                new PaymentProcessor(), taxCalculator
        );
        ConsoleInput input = new ConsoleInput(new Scanner(System.in));
        ConsoleMenu consoleMenu = new ConsoleMenu(input, orderService);
        consoleMenu.start();
    }
}
