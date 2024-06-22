package xyz.whinyaan;

// Mikyla
public class ShoppingCart {
    public void addItemToCart(float price, float unitNum, String unit, int quantity)
    {
        System.out.println( "Added Item" );
        return;
    }

    public void deleteItemInCart()
    {
        System.out.println( "Deleted Item" );
        return;
    }

    public void checkout()
    {
        System.out.println( "Checkout" );

        Payment payment = new Payment();
        payment.payment();

        return;
    }

    public void viewCart()
    {
        System.out.println( "Print Cart Contents" );
        return;
    }
}
