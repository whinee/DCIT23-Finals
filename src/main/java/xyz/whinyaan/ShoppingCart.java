package xyz.whinyaan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

// Mikyla
public class ShoppingCart {
    private Map<Item, Integer> cartItems;

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        this.cartItems.put(item, this.cartItems.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(Item item) {
        this.cartItems.remove(item);
    }

    public void updateItemQuantity(Item item, int quantity) {
        if (quantity <= 0) {
            removeItem(item);
        } else {
            this.cartItems.put(item, quantity);
        }
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Item, Integer> entry : cartItems.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public Map<Item, Integer> getCartItems() {
        return this.cartItems;
    }
}

private static void showCart() {
    JFrame frame = new JFrame("Shopping Cart");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    frame.setLayout(new BorderLayout());

    String[] columnNames = {"Item", "Unit Price", "Quantity", "Total"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    JTable table = new JTable(model);

    for (Map.Entry<Item, Integer> entry : cart.getCartItems().entrySet()) {
        Item item = entry.getKey();
        int quantity = entry.getValue();
        double itemTotal = item.getPrice() * quantity;
        model.addRow(new Object[]{item.getName(), item.getPrice(), quantity, itemTotal});
    }

    JScrollPane scrollPane = new JScrollPane(table);
    frame.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    JButton checkoutButton = new JButton("Checkout");
    JButton backButton = new JButton("Back");

    checkoutButton.addActionListener(e -> {
        frame.dispose();
        checkout();
    });

    backButton.addActionListener(e -> {
        frame.dispose();
        showSectionMenu();
    });

    buttonPanel.add(checkoutButton);
    buttonPanel.add(backButton);
    frame.add(buttonPanel, BorderLayout.SOUTH);

    frame.setVisible(true);
}
