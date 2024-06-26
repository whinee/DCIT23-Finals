package xyz.whinyaan;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SectionSelector extends JDialog {
    private JComboBox<String> comboBox;
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton cartButton = new JButton("View Cart");

    private String selectedSection;
    private static boolean cartDisplayed = false;

    public SectionSelector(JFrame parent, String[] sections) {
        super(parent, "Sections", true);
        
        comboBox = new JComboBox<>(sections);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select a section:"));
        panel.add(comboBox);
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(cartButton);

        okButton.addActionListener(e -> {
            parent.dispose();
            dispose();
            selectedSection = (String) comboBox.getSelectedItem();
        });

        cancelButton.addActionListener(e -> dispose());

        cartButton.addActionListener(e -> {
            parent.dispose();
            dispose();
            cartDisplayed = true;
            ShoppingCart.showCart();
        });

        
        getContentPane().add(panel);
        pack();

        setLocationRelativeTo(parent);

        // parent.dispose();
    }

    public String showDialog() {
        setVisible(true);
        return selectedSection;
    }

    public static void main() {
        String[] sections = {
            "Fruits and Vegetable",
            "Frozen Meats",
            "Canned/Jarred Goods",
            "Dairy and Baking Goods",
            "Beverage",
            "Personal Care and Cleaners",
        };

        JFrame dialog = new JFrame();
        dialog.setVisible(true);

        SectionSelector sectionSelector = new SectionSelector(dialog, sections);
        String selectedSection = sectionSelector.showDialog();

        if (cartDisplayed) {
            dialog.dispose();
            cartDisplayed = false;
        } else if (selectedSection == null) {
            dialog.dispose();
            App.anotherTransaction();
        } else {
            dialog.dispose();
            SelectItem selectItem = new SelectItem();
            selectItem.selectItem(selectedSection);
        }

    }
}