package xyz.whinyaan;

import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

public class App {
    // Arriola
    public static void main(String[] args) throws Exception {
        UIManager.put("OptionPane.messageFont", new Font(
            "Arial", Font.BOLD, 14
        ));
        UIManager.put("OptionPane.messageForeground", Color.BLUE);
        UIManager.put("OptionPane.buttonFont", new Font(
            "Arial", Font.PLAIN, 12
        ));
        UIManager.put("OptionPane.background", new Color(255, 255, 204));
        UIManager.put("Panel.background", new Color(255, 255, 204));
        UIManager.put("Button.background", new Color(204, 229, 255));

        User login = new User();
        login.login();
    }
}
