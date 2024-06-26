package xyz.whinyaan;

import java.util.Optional;
import java.util.Scanner;

// Carias
// implement in javax.swing.JOptionPane instead of java.util.Scanner
// make sure other modules can call ur modules
// dont change the names of other modules/functions
public class Payment {
    public static void payment(double totalPrice) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        cart.displayCart();

        System.out.println("Choose a payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");

        int paymentMethodChoice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (paymentMethodChoice) {
            case 1:
                handleCashPayment(totalPrice);
                break;
            case 2:
                handleCardPayment(totalPrice);
                break;
            default:
                System.out.println("Invalid payment method choice.");
        }
    }

    // reimplement
    // pass items in cart to printReceipt as argument instead of grabbing it using cart.items
    public static void printReceipt(double totalPrice, Optional<Double> change) {
        // ShoppingCart cart = new ShoppingCart();
        // System.out.println("\n---- Receipt ----");
        // for (Item item : cart.items) {
        //     System.out.printf("%s: %d %s @ %.2f each\n", item.name, item.quantity, item.unit, item.price);
        // }
        // System.out.printf("Total Price: %.2f\n", totalPrice);
        // change.ifPresent(c -> System.out.printf("Change: %.2f\n", c));
        // System.out.println("-----------------");
    }

    // implement this
    public static void handleCashPayment(double totalPrice) {
    }

    public static void handleCardPayment(double totalPrice) {
        // remove number of attempts. let the user recurse/repeat as much as they want
        int attempts = 3;

        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        cart.clearCart(); // Clear the cart after successful checkout

        while (attempts > 0) {
            System.out.print("Enter card verification value (CVV): ");
            String cvvStr = scanner.nextLine();
            try {
                int cvv = Integer.parseInt(Optional.ofNullable(cvvStr).orElse("0"));
                if (validateCVV(cvv)) {
                    printReceipt(totalPrice, Optional.empty());
                    return;
                } else {
                    attempts--;
                    System.out.println("Invalid CVV. Please try again. Attempts remaining: " + attempts);
                }
            } catch (NumberFormatException e) {
                attempts--;
                System.out.println("Invalid CVV. Please enter a valid number. Attempts remaining: " + attempts);
            }
        }
        System.out.println("Transaction failed. Maximum attempts reached.");
    }

    // CVV cant be validated. remove this function
    // instead, validate credit/debit card number using Luhn's algorithm
    // https://www.dcode.fr/luhn-algorithm
    public static boolean validateCVV(int cvv) {
        // CVV validation
        return cvv == 123; // Assume 123 is the correct CVV (idk tayo ba magset?)
    }
}
