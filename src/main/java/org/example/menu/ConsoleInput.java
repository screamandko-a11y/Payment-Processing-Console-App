package org.example.menu;

import lombok.RequiredArgsConstructor;

import java.util.Scanner;


// created this class guided by SRP move input handling to this class
@RequiredArgsConstructor
public class ConsoleInput {
    private final Scanner scanner;

    public String readString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readString(prompt).trim());
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid integer.");
            }
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("Enter a number greater than zero.");
        }
    }

    public double readNonNegativeDouble(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                double number = Double.parseDouble(value);

                if (Double.isFinite(number) && number >= 0) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Enter a valid non-negative number.");
        }
    }
}
