package xyz.whinyaan;

import javax.swing.JOptionPane;

//Carias
public class Payment {
    public static void payment(double totalPrice) {
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
                break;
            case 1:
                handleCardPayment(totalPrice);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid payment method choice.");
        }
    }

    private static void handleCashPayment(double totalPrice) {
        String amountStr = JOptionPane.showInputDialog(null, "Enter the amount paid:");
        double amountPaid = Double.parseDouble(amountStr);
        if (amountPaid >= totalPrice) {
            double change = amountPaid - totalPrice;
            JOptionPane.showMessageDialog(
                null,
                "Payment successful. Change: " + change);
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient amount. Payment failed.");
        }
    }

    private static void handleCardPayment(double totalPrice) {
        String cardNumber = JOptionPane.showInputDialog(null, "Enter card number:");
        String cardHolder = JOptionPane.showInputDialog(
            null,
            "Enter card holder name:");
        String expiryDate = JOptionPane.showInputDialog(
            null,
            "Enter card expiry date (MM/YY):");
        String cvv = JOptionPane.showInputDialog(
            null,
            "Enter card CVV:");

        if (isValidCardNumber(cardNumber)) {
            JOptionPane.showMessageDialog(null, "Card payment successful.");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid card number. Payment failed.");
        }
    }

    private static boolean isValidCardNumber(String cardNumber) {
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

    public static void main(String[] args) {
        // Example usage
        payment(100.0);
    }
}
