package xyz.whinyaan;

// Mikyla
public class ShoppingCart {
    private static void selectItemsFromSection(String section) {
        List<Item> items = getItemsBySection(section);
        boolean addItem = true;

        while (addItem) {
            System.out.println("Choose an item:");
            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.printf("%d. %s (%s @ %.2f)\n", i + 1, item.name, item.unit, item.price);
            }

            int itemChoice = scanner.nextInt();
            scanner.nextLine(); 

            if (itemChoice < 1 || itemChoice > items.size()) {
                System.out.println("Invalid item choice.");
                continue;
            }

            Item selectedItem = items.get(itemChoice - 1);

            System.out.printf("Enter quantity of %s (%s): ", selectedItem.name, selectedItem.unit);
            int quantity = scanner.nextInt();
            scanner.nextLine(); 

            if (quantity <= 0) {
                System.out.println("Quantity must be a positive number.");
                continue;
            }

            cart.addItem(selectedItem, quantity);
            System.out.println(selectedItem.name + " added to cart.");

            cart.displayCart(); 

            System.out.println("Do you want to add another item? (yes/no)");
            String addAnotherItemOption = scanner.nextLine();

            if (addAnotherItemOption.equalsIgnoreCase("no")) {
                addItem = false;
            }
        }
    }

    
