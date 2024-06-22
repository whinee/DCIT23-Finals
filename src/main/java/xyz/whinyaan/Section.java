package xyz.whinyaan;

import javax.swing.JOptionPane;

// Arriola
public class Section {
    public void anotherTransaction()
    {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to make another transaction?", "Select an Option", JOptionPane.YES_NO_OPTION);
        User login = new User();

        if (response == JOptionPane.YES_OPTION) {
            selectSection();
        } else if (response == JOptionPane.NO_OPTION) {
            login.login();
        } else {
            login.login();
        }
    }
    public void selectSection()
    {
        String[] sections = {"Fruits and Vegetable", "Frozen Meats", "Canned/Jarred Goods", "Dairy and Baking Goods", "Beverage", "Personal Care and Cleaners"};

        String section = (String) JOptionPane.showInputDialog(
                null,
                "Select a section:",
                "Sections",
                JOptionPane.PLAIN_MESSAGE,
                null,
                sections,
                sections[0]
        );

        if (section == null) {
            anotherTransaction();
            return;
        }

        Item item = new Item();
        item.selectItem(section);
    }
}
