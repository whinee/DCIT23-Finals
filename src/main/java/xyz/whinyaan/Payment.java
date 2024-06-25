package xyz.whinyaan;

// Carias
public class Payment {
    private static void handleCardPayment(double totalPrice) {
        int attempts = 3;
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

    private static boolean validateCVV(int cvv) {
        // CVV validation
        return cvv == 123; // Assume 123 is the correct CVV (idk tayo ba magset?)
    }

    private static void printReceipt(double totalPrice, Optional<Double> change) {
        System.out.println("\n---- Receipt ----");
        for (Item item : cart.items) {
            System.out.printf("%s: %d %s @ %.2f each\n", item.name, item.quantity, item.unit, item.price);
        }
        System.out.printf("Total Price: %.2f\n", totalPrice);
        change.ifPresent(c -> System.out.printf("Change: %.2f\n", c));
        System.out.println("-----------------");
    }
}
