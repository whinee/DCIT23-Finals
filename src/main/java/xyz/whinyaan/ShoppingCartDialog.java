package xyz.whinyaan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class ShoppingCartDialog extends JDialog {
    public ShoppingCartDialog(JDialog parent,
            HashMap<String, List<Object>> cartItems) {
        super(parent, "Shopping Cart", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        Color backgroundColor = new Color(19, 21, 32);
        Color secondaryBackgroundColor = new Color(50, 56, 78);
        Color foregroundColor = Color.WHITE;

        parent.setBackground(backgroundColor);

        String[] columnNames = { "Item", "Unit Price", "Unit", "Quantity",
                "Total" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        table.setBackground(backgroundColor);
        table.setForeground(foregroundColor);
        table.setSelectionBackground(secondaryBackgroundColor);
        table.setSelectionForeground(foregroundColor);
        table.setGridColor(foregroundColor); // Set table and header colors

        table.setFont(new Font("Arial", Font.PLAIN, 12));

        table.getTableHeader().setBackground(backgroundColor);
        table.getTableHeader().setForeground(foregroundColor);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Populate the table
        for (HashMap.Entry<String, List<Object>> entry : cartItems.entrySet()) {
            List<Object> value = entry.getValue();
            Item item = (Item) value.get(0);
            int quantity = ((Number) value.get(1)).intValue();
            double total = ((Number) value.get(2)).doubleValue();
            model.addRow(new Object[] {
                    entry.getKey(),
                    item.getPrice(),
                    item.getUnitNum() + " " + item.getUnit(),
                    quantity,
                    total });
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(backgroundColor);
        scrollPane.getViewport().setBackground(backgroundColor);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(UIManager.getColor("Panel.background")); // Set
                                                                           // the
                                                                           // button
                                                                           // panel
                                                                           // background

        JButton checkoutButton = new JButton("Checkout");
        JButton backButton = new JButton("Back");

        checkoutButton.addActionListener(e -> {
            dispose();
            Payment.payment(ShoppingCart.calculateTotal());
        });

        backButton.addActionListener(e -> {
            dispose();
            SectionSelector.main();
        });

        buttonPanel.add(checkoutButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public static void main() {
    }
}