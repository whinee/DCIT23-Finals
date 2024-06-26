package xyz.whinyaan;

// Mikyla
public class ShoppingCart {
    public void addItemToCart(
        float price,
        float unitNum,
        String unit,
        int quantity
    ) {
        System.out.println( "Added Item" );
    }

    public void deleteItemInCart()
    {
        System.out.println( "Deleted Item" );
    }

    public void checkout()
    {
        System.out.println( "Checkout" );

        Payment.payment(100);
    }

    public void clearCart()
    {
        System.out.println( "Print Cart Contents" );
    }

    public void displayCart()
    {
        System.out.println( "Print Cart Contents" );
    }
}