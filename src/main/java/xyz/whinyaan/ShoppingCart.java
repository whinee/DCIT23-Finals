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

        Payment payment = new Payment();
        payment.payment();
    }

    public void viewCart()
    {
        System.out.println( "Print Cart Contents" );
    }
}
