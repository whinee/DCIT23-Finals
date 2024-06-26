package xyz.whinyaan;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

// Arriola
public class SelectItem {
    private static final HashMap<String, HashMap<String, Item>> sectionsItems = new HashMap<>();
    static {
        HashMap<String, Item> fruitsAndVegetables = new HashMap<>();
        fruitsAndVegetables.put("Apple", new Item(20.50, 1, "piece"));
        fruitsAndVegetables.put("Banana", new Item(8, 1, "piece"));
        fruitsAndVegetables.put("Tomato", new Item(150.75, 1, "kilo"));
        fruitsAndVegetables.put("Potato", new Item(90, 0.5, "kilo"));
        fruitsAndVegetables.put("Strawberry", new Item(206.50, 0.25, "kilo"));
        sectionsItems.put("Fruits and Vegetable", fruitsAndVegetables);

        HashMap<String, Item> frozenMeats = new HashMap<>();
        frozenMeats.put("Fish", new Item(250.75, 1, "kilo"));
        frozenMeats.put("Pork Meat", new Item(170, 1, "kilo"));
        frozenMeats.put("Chicken", new Item(180.50, 1, "kilo"));
        frozenMeats.put("Beef Meat", new Item(500.25, 1, "kilo"));
        sectionsItems.put("Frozen Meats", frozenMeats);

        HashMap<String, Item> cannedJarredGoods = new HashMap<>();
        cannedJarredGoods.put("555 Tuna", new Item(25.25, 1, "piece"));
        cannedJarredGoods.put("Argentina Corned beef", new Item(
            30.75, 1, "piece"
        ));
        cannedJarredGoods.put("Maling", new Item(53, 1, "piece"));
        cannedJarredGoods.put("Silver swan", new Item(15.75, 1, "piece"));
        cannedJarredGoods.put("UFC Ketchup", new Item(11.50, 1, "piece"));
        cannedJarredGoods.put("Wow Ulam", new Item(23, 1, "piece"));
        cannedJarredGoods.put("Spam", new Item(109.50, 1, "piece"));
        cannedJarredGoods.put("San Marino", new Item(30.75, 1, "piece"));
        sectionsItems.put("Canned/Jarred Goods", cannedJarredGoods);

        HashMap<String, Item> dairyAndBakingGoods = new HashMap<>();
        dairyAndBakingGoods.put("Butter", new Item(35.25, 1, "piece"));
        dairyAndBakingGoods.put("Star Margarine", new Item(30.75, 1, "piece"));
        dairyAndBakingGoods.put("Flour", new Item(40, 1, "kilo"));
        sectionsItems.put("Dairy and Baking Goods", dairyAndBakingGoods);

        HashMap<String, Item> beverage = new HashMap<>();
        beverage.put("Gin", new Item(42.75, 1, "piece"));
        beverage.put("Zest-o", new Item(12, 1, "piece"));
        beverage.put("Vita milk", new Item(35, 1, "piece"));
        beverage.put("Cobra", new Item(24.50, 1, "piece"));
        beverage.put("Bearbrand Sterilized", new Item(
            38.25, 1, "piece"
        ));
        beverage.put("Red horse", new Item(110.00, 1, "piece"));
        sectionsItems.put("Beverage", beverage);

        HashMap<String, Item> personalCareAndCleaners = new HashMap<>();
        personalCareAndCleaners.put("Tissue", new Item(11.50, 1, "piece"));
        personalCareAndCleaners.put("Joy", new Item(10, 1, "piece"));
        personalCareAndCleaners.put("Safeguard", new Item(19.75, 1, "piece"));
        personalCareAndCleaners.put("Mr. clean (bar)", new Item(27.25, 1, "piece"));
        personalCareAndCleaners.put("Downy", new Item(15, 1, "piece"));
        personalCareAndCleaners.put("Tender care (powder)", new Item(
            21, 1, "piece"
        ));
        personalCareAndCleaners.put("Modess (8 pcs)", new Item(47.25, 1, "pack"));
        personalCareAndCleaners.put("Gatsby", new Item(50.75, 1, "piece"));
        sectionsItems.put("Personal Care and Cleaners", personalCareAndCleaners);

    }

    public void selectItem(String section)
    {

        HashMap<String, Item> items = sectionsItems.get(section);

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

        SectionSelector.main();
    }
}
