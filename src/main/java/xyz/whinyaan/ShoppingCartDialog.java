package xyz.whinyaan;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShoppingCartDialog extends JDialog {
    public ShoppingCartDialog(JDialog parent, HashMap<String, List<Object>> cartItems) {
        super(parent, "Shopping Cart", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SectionSelector.main();
            }
        });

        String[] columnNames = {"Item", "Unit Price", "Unit", "Quantity", "Total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        // Populate the table
        for (HashMap.Entry<String, List<Object>> entry : cartItems.entrySet()) {
            List<Object> value = entry.getValue();
            Item item = (Item) value.get(0);
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
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton checkoutButton = new JButton("Checkout");
        JButton backButton = new JButton("Back");

        checkoutButton.addActionListener(e -> {
            dispose();
            Payment.payment(ShoppingCart.calculateTotal());
        });

        backButton.addActionListener(e -> {
            dispose();
            // SectionSelector.main();
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