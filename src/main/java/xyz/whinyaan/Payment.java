package xyz.whinyaan;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JOptionPane;

//Carias
public class Payment {
    public static void payment(double totalPrice) {
        while (true) {
            String[] options = {"Cash", "Card"};
            int paymentMethodChoice = JOptionPane.showOptionDialog(null,
                    "Choose a payment method:",
                    "Payment Method",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (paymentMethodChoice) {
                case 0:
                    handleCashPayment(totalPrice);
                case 1:
                    handleCardPayment(totalPrice);
                default:
                    JOptionPane.showMessageDialog(
                        null,
                        "Invalid payment method choice.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void handleCashPayment(double totalPrice) {
        while (true) {
            String amountStr = JOptionPane.showInputDialog(null, "Enter the amount paid:");
            double amountPaid = Double.parseDouble(amountStr);
            if (amountPaid >= totalPrice) {
                double change = amountPaid - totalPrice;
                JOptionPane.showMessageDialog(
                    null,
                    "Payment successful. Change: " + change,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Insufficient amount. Payment failed.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        App app = new App();
        app.anotherTransaction();
    }

    private static void handleCardPayment(double totalPrice) {
        while (true) {
            String cardNumber = JOptionPane.showInputDialog(null, "Enter card number:");

            if (!isValidCardNumber(cardNumber)) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid card number. Payment failed. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                continue;
            }

            JOptionPane.showInputDialog(
                null,
                "Enter card holder name:");
            String expiryDate = JOptionPane.showInputDialog(
                null,
                "Enter card expiry date (MM/YY):");
    
            if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid expiry date. Payment failed. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                continue;
            }

            try {
                // Parse the expiry date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                YearMonth expiry = YearMonth.parse(expiryDate, formatter);

                // Get the current date
                YearMonth now = YearMonth.now();

                // Check if the expiry date is in the future
                if (expiry.isAfter(now) || expiry.equals(now)) {
                    JOptionPane.showMessageDialog(
                    null,
                    "The card has already expired. Payment failed. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    continue;
                }
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid expiry date. Payment failed. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                continue;
            }
            String CVV = JOptionPane.showInputDialog(
                null,
                "Enter card CVV:");

            if (!(CVV.length() == 3 || CVV.length() == 4)) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid CVV. Payment failed. Try again.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                    continue;
                }

            JOptionPane.showMessageDialog(
                null,
                "Card payment successful. The amount of " + totalPrice +
                " has been deducted from your bank account.",
                "SuccessApp app = new App();\n" + //
                                        "            app.anotherTransaction();",
                JOptionPane.INFORMATION_MESSAGE);

            break;
        }

        App app = new App();
        app.anotherTransaction();
    }

    private static boolean isValidCardNumber(String cardNumber) {
        // There are some instances where a bank can issue up to 19 digits in a card
        // if (!(cardNumber.length() == 16)) {
        //     return false;
        // }

        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
