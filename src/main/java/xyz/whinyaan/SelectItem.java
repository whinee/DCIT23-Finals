package xyz.whinyaan;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

// Arriola
public class SelectItem {
    public void selectItem(String section) throws IllegalStateException
    {
        HashMap<String, Item> items = new HashMap<>();
        switch (section) {
            case "Fruits and Vegetable":
                items.put("Apple", new Item(20.50, 1, "piece"));
                items.put("Banana", new Item(8, 1, "piece"));
                items.put("Tomato", new Item(150.75, 1, "kilo"));
                items.put("Potato", new Item(90, 0.5, "kilo"));
                items.put("Strawberry", new Item(206.50, 0.25, "kilo"));
                break;
            case "Frozen Meats":
                items.put("Fish", new Item(250.75, 1, "kilo"));
                items.put("Pork Meat", new Item(170, 1, "kilo"));
                items.put("Chicken", new Item(180.50, 1, "kilo"));
                items.put("Beef Meat", new Item(500.25, 1, "kilo"));
                break;
            case "Canned/Jarred Goods":
                items.put("555 Tuna", new Item(25.25, 1, "piece"));
                items.put("Argentina Corned beef", new Item(
                    30.75, 1, "piece"
                ));
                items.put("Maling", new Item(53, 1, "piece"));
                items.put("Silver swan", new Item(15.75, 1, "piece"));
                items.put("UFC Ketchup", new Item(11.50, 1, "piece"));
                items.put("Wow Ulam", new Item(23, 1, "piece"));
                items.put("Spam", new Item(109.50, 1, "piece"));
                items.put("San Marino", new Item(30.75, 1, "piece"));
                break;
            case "Dairy and Baking Goods":
                items.put("Butter", new Item(35.25, 1, "piece"));
                items.put("Star Margarine", new Item(30.75, 1, "piece"));
                items.put("Flour", new Item(40, 1, "kilo"));
                break;
            case "Beverage":
                items.put("Gin", new Item(42.75, 1, "piece"));
                items.put("Zest-o", new Item(12, 1, "piece"));
                items.put("Vita milk", new Item(35, 1, "piece"));
                items.put("Cobra", new Item(24.50, 1, "piece"));
                items.put("Bearbrand Sterilized", new Item(
                    38.25, 1, "piece"
                ));
                items.put("Red horse", new Item(110.00, 1, "piece"));
                break;
            case "Personal Care and Cleaners":
                items.put("Tissue", new Item(11.50, 1, "piece"));
                items.put("Joy", new Item(10, 1, "piece"));
                items.put("Safeguard", new Item(19.75, 1, "piece"));
                items.put("Mr. clean (bar)", new Item(27.25, 1, "piece"));
                items.put("Downy", new Item(15, 1, "piece"));
                items.put("Tender care (powder)", new Item(
                    21, 1, "piece"
                ));
                items.put("Modess (8 pcs)", new Item(47.25, 1, "pack"));
                items.put("Gatsby", new Item(50.75, 1, "piece"));
                break;
            default:
                throw new IllegalStateException("Code should not reach here.");
        }

        ArrayList<String> keysList = new ArrayList<>(items.keySet());
        String[] keys = keysList.toArray(String[]::new);

        String itemName = (String) JOptionPane.showInputDialog(
                null,
                "Select an item:",
                "Items",
                JOptionPane.PLAIN_MESSAGE,
                null,
                keys,
                keys[0]
        );

        if (itemName == null) {
            App app = new App();
            app.anotherTransaction();
            return;
        }

        Item item = items.get(itemName);
        double unitNum = item.getUnitNum();
        String unit = item.getUnit();

        int quantity = 0;

        while (true) {
            String input = JOptionPane.showInputDialog(
                null, "Enter number of " + unitNum + " " + unit + ":"
            );

            if (input == null) {
                App app = new App();
                app.anotherTransaction();
                return;
            }
            
            try {
                quantity = Integer.parseInt(input);
                if (quantity > 0) {
                    break;
                }
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid input. Please try again and enter a non-negative number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Invalid input. Please try again and enter a valid number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(itemName, item, quantity);
    }
}
