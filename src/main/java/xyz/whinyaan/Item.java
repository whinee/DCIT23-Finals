package xyz.whinyaan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

// Arriola
public class Item {
    public void selectItem(String section)
    {
        System.out.println(section);
        HashMap<String, List<Object>> items = new HashMap<>();
        switch (section) {
            case "Fruits and Vegetable":
                items.put("Apple", Arrays.asList(20.50, 1, "piece"));
                items.put("Banana", Arrays.asList(8, 1, "piece"));
                items.put("Tomato", Arrays.asList(150.75, 1, "kilo"));
                items.put("Potato", Arrays.asList(90, 0.5, "kilo"));
                items.put("Strawberry", Arrays.asList(206.50, 0.25, "kilo"));
                break;
            case "Frozen Meats":
                items.put("Fish", Arrays.asList(250.75, 1, "kilo"));
                items.put("Pork Meat", Arrays.asList(170, 1, "kilo"));
                items.put("Chicken", Arrays.asList(180.50, 1, "kilo"));
                items.put("Beef Meat", Arrays.asList(500.25, 1, "kilo"));
                break;
            case "Canned/Jarred Goods":
                items.put("555 Tuna", Arrays.asList(25.25, 1, "piece"));
                items.put("Argentina Corned beef", Arrays.asList(30.75, 1, "piece"));
                items.put("Maling", Arrays.asList(53, 1, "piece"));
                items.put("Silver swan", Arrays.asList(15.75, 1, "piece"));
                items.put("UFC Ketchup", Arrays.asList(11.50, 1, "piece"));
                items.put("Wow Ulam", Arrays.asList(23, 1, "piece"));
                items.put("Spam", Arrays.asList(109.50, 1, "piece"));
                items.put("San Marino", Arrays.asList(30.75, 1, "piece"));
                break;
            case "Dairy and Baking Goods":
                items.put("Butter", Arrays.asList(35.25, 1, "piece"));
                items.put("Star Margarine", Arrays.asList(30.75, 1, "piece"));
                items.put("Flour", Arrays.asList(40, 1, "kilo"));
                break;
            case "Beverage":
                items.put("Gin", Arrays.asList(42.75, 1, "piece"));
                items.put("Zest-o", Arrays.asList(12, 1, "piece"));
                items.put("Vita milk", Arrays.asList(35, 1, "piece"));
                items.put("Cobra", Arrays.asList(24.50, 1, "piece"));
                items.put("Bearbrand Sterilized", Arrays.asList(38.25, 1, "piece"));
                items.put("Red horse", Arrays.asList(110.00, 1, "piece"));
                break;
            case "Personal Care and Cleaners":
                items.put("Tissue", Arrays.asList(11.50, 1, "piece"));
                items.put("Joy", Arrays.asList(10, 1, "piece"));
                items.put("Safeguard", Arrays.asList(19.75, 1, "piece"));
                items.put("Mr. clean (bar)", Arrays.asList(27.25, 1, "piece"));
                items.put("Downy", Arrays.asList(15, 1, "piece"));
                items.put("Tender care (powder)", Arrays.asList(21, 1, "piece"));
                items.put("Modess (8 pcs)", Arrays.asList(47.25, 1, "pack"));
                items.put("Gatsby", Arrays.asList(50.75, 1, "piece"));
                break;
        }

        ArrayList<String> keysList = new ArrayList<>(items.keySet());
        String[] keys = keysList.toArray(new String[keysList.size()]);

        String item = (String) JOptionPane.showInputDialog(
                null,
                "Select an item:",
                "Items",
                JOptionPane.PLAIN_MESSAGE,
                null,
                keys,
                keys[0]
        );

        List<Object> value = items.get(item);

        float price = ((Number) value.get(0)).floatValue();
        float unitNum = ((Number) value.get(1)).floatValue();
        String unit = (String) value.get(2);
        int quantity = 0;

        while (true) {
            String input = JOptionPane.showInputDialog(null, "Enter number of " + unitNum + " " + unit + ":" );
            
            try {
                quantity = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please try again and enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItemToCart(price, unitNum, unit, quantity);

        return;
    }
}
