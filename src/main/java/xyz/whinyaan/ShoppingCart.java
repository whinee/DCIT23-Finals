package xyz.whinyaan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;

// Mikyla
public class ShoppingCart {
    private static HashMap<String, List<Object>> cartItems = new HashMap<>();

    public void addItem(String itemName, Item item, int quantity) {
        if (cartItems.containsKey(itemName)) {
            List<Object> itemDetails = cartItems.get(itemName);
            quantity = (int) itemDetails.get(1) + quantity;
        }
    
        double total = item.getPrice() * quantity;
        ShoppingCart.cartItems.put(
            itemName,
            Arrays.asList(
                item,
                quantity,
                total));

        SectionSelector.main();
    }

    public void removeItem(String itemName) {
        ShoppingCart.cartItems.remove(itemName);
    }

    // public void updateItemQuantity(String itemName, Item item, int quantity) {
    //     if (quantity > 0) {
    //         addItem(itemName, item, quantity);
    //     }
    // }

    public static double calculateTotal() {
        double total = 0.0;
        for (HashMap.Entry<String, List<Object>> entry : cartItems.entrySet()) {
            List<Object> value = entry.getValue();
            double itemTotal = ((Number) value.get(2)).doubleValue();
            total += itemTotal;
        }
        return total;
    }

    public HashMap<String, List<Object>> getCartItems() {
        return ShoppingCart.cartItems;
    }

    public static void showCart() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);

        ShoppingCartDialog shoppingCartDialog = new ShoppingCartDialog(dialog, cartItems);
        shoppingCartDialog.main();
    }

}


