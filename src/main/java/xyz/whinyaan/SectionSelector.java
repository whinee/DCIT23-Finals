package xyz.whinyaan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class SectionSelector extends JDialog {
    private JComboBox<String> comboBox;
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final JButton cartButton = new JButton("View Cart");

    private String selectedSection;
    private static boolean cartDisplayed = false;

    public SectionSelector(JFrame parent, String[] sections) {
        super(parent, "Sections", true);
        setLayout(new BorderLayout());
    
        comboBox = new JComboBox<>(sections);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel sectionLabel = new JLabel("Select a section:");
        sectionLabel.setFont(UIManager.getFont("OptionPane.messageFont"));
        
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.add(sectionLabel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(labelPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(comboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(okButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(cancelButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(cartButton, gbc);

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