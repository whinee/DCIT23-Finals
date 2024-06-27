package xyz.whinyaan;

import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

//Carias
public class Payment {
    public static void payment(
        double totalPrice,
        HashMap<String, List<Object>> cartItems
    ) {
        while (true) {
            String[] options = { "Cash", "Card" };
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
                    handleCashPayment(totalPrice, cartItems);
                case 1:
                    handleCardPayment(totalPrice, cartItems);
                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid payment method choice.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void handleCashPayment(
            double totalPrice,
            HashMap<String, List<Object>> cartItems
        ) {
        while (true) {
            String amountStr = JOptionPane.showInputDialog(null,
                    "Enter the amount paid:");
            double amountPaid = Double.parseDouble(amountStr);
            if (amountPaid >= totalPrice) {
                double change = amountPaid - totalPrice;
                JOptionPane.showMessageDialog(
                        null,
                        "Payment successful. Change: " + change,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = new JDialog();
                dialog.setVisible(true);
                new Receipt(dialog, amountPaid, cartItems);
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

    private static void handleCardPayment(
        double totalPrice,
        HashMap<String, List<Object>> cartItems
    ) {
        while (true) {
            String cardNumber = JOptionPane.showInputDialog(null,
                    "Enter card number:");

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

            JDialog dialog = new JDialog();
            dialog.setVisible(true);
            new Receipt(dialog, 0, cartItems);

            break;
        }

        App.anotherTransaction();
    }

    private static boolean isValidCardNumber(String cardNumber) {
        // There are some instances where a bank can issue up to 19 digits in a
        // card
        // if (!(cardNumber.length() == 16)) {
        // return false;
        // }

        int sum = 0;
        boolean alternate = false;
        if (cardNumber == null) {
            return false;
        }
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
