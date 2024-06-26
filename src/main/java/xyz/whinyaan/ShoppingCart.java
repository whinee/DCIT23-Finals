package xyz.whinyaan;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Mikyla
public class ShoppingCart {
    private static HashMap<String, List<Object>> cartItems = new HashMap<>();

    public void addItem(String itemName, Item item, int quantity) {
        if (cartItems.containsKey(itemName)) {
            List<Object> itemDetails = cartItems.get(itemName);
            quantity = (int) itemDetails.get(1) + quantity;
        }
    
        double total = item.getPrice() * quantity;
        ShoppingCart.cartItems.put(
            itemName,
            Arrays.asList(
                item,
                quantity,
                total));
    }

    public void removeItem(String itemName) {
        ShoppingCart.cartItems.remove(itemName);
    }

    public void updateItemQuantity(String itemName, Item item, int quantity) {
        if (quantity > 0) {
            addItem(itemName, item, quantity);
        }
    }

    public static double calculateTotal() {
        double total = 0.0;
        for (HashMap.Entry<String, List<Object>> entry : cartItems.entrySet()) {
            List<Object> value = entry.getValue();
            double itemTotal = ((Number) value.get(2)).doubleValue();
            total += itemTotal;
        }
        return total;
    }

    public HashMap<String, List<Object>> getCartItems() {
        return ShoppingCart.cartItems;
    }

    public static void showCart() {
        JFrame frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
    
        String[] columnNames = {"Item", "Unit Price", "Quantity", "Total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        for (HashMap.Entry<String, List<Object>> entry : cartItems.entrySet()) {
            List<Object> value = entry.getValue();
            Item item = ((Item) value.get(0));
            int quantity = ((Number) value.get(1)).intValue();
            double total = ((Number) value.get(2)).doubleValue();
            model.addRow(new Object[]{
                entry.getKey(),
                item.getPrice(),
                item.getUnitNum() + " " + item.getUnit(),
                quantity,
                total});
        }
    
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel();
        JButton checkoutButton = new JButton("Checkout");
        JButton backButton = new JButton("Back");
    
        checkoutButton.addActionListener(e -> {
            frame.dispose();

            Payment.payment(calculateTotal());
        });
    
        backButton.addActionListener(e -> {
            frame.dispose();
            // showSectionMenu();
        });
    
        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    
        frame.setVisible(true);
    }
}


