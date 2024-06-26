package xyz.whinyaan;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SectionSelector extends JDialog {
    private JComboBox<String> comboBox;
    private JButton okButton;
    private JButton cancelButton;
    private JButton cartButton;

    private String selectedSection;

    public SectionSelector(JDialog parent, String[] sections) {
        super(parent, "Sections", true);
        
        comboBox = new JComboBox<>(sections);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        cartButton = new JButton("View Cart");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select a section:"));
        panel.add(comboBox);
        panel.add(okButton);
        panel.add(cancelButton);
        panel.add(cartButton);

        okButton.addActionListener(e -> {
            dispose();
            selectedSection = (String) comboBox.getSelectedItem();
        });

        cancelButton.addActionListener(e -> dispose());

        cartButton.addActionListener(e -> {
            dispose();
            ShoppingCart.showCart();
        });

        getContentPane().add(panel);
        pack();
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

        JDialog dialog = new JDialog();
        dialog.setModal(true);

        SectionSelector sectionSelector = new SectionSelector(dialog, sections);
        String selectedSection = sectionSelector.showDialog();

        if (selectedSection == null) {
            App app = new App();
            // app.anotherTransaction();
            return;
        } else {
            SelectItem selectItem = new SelectItem();
            selectItem.selectItem(selectedSection);
        }

        dialog.dispose();
    }
}