package xyz.whinyaan;

import javax.swing.JOptionPane;

// Arriola
public class Section {
    public void selectSection() throws IllegalStateException
    {
        String[] sections = {
            "Fruits and Vegetable",
            "Frozen Meats",
            "Canned/Jarred Goods",
            "Dairy and Baking Goods",
            "Beverage",
            "Personal Care and Cleaners",
        };

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
            App app = new App();
            app.anotherTransaction();
            return;
        }

        SelectItem selectItem = new SelectItem();
        selectItem.selectItem(section);
    }
}
