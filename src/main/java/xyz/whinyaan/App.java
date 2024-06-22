package xyz.whinyaan;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

/**
 * Hello world!
 *
 */
public class App 
{
    // Arriola
    public static void main( String[] args )
    {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
        UIManager.put("OptionPane.messageForeground", Color.BLUE);
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        UIManager.put("OptionPane.background", new Color(255, 255, 204)); // Light yellow background
        UIManager.put("Panel.background", new Color(255, 255, 204)); // Light yellow background for panel
        UIManager.put("Button.background", new Color(204, 229, 255)); // Light blue background for buttons

        User login = new User();
        login.login();
        
        return;
    }
}
