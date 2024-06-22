package xyz.whinyaan;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

// Arriola
public class App {
    public void anotherTransaction() throws Exception
    {
        int response = JOptionPane.showConfirmDialog(
            null,
            "Do you want to make another transaction?",
            "Select an Option",
            JOptionPane.YES_NO_OPTION
        );
        User login = new User();

        if (response == JOptionPane.YES_OPTION) {
            Section section = new Section();
            section.selectSection();
        } else if (response == JOptionPane.NO_OPTION) {
            login.login();
        } else {
            login.login();
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.put("OptionPane.messageFont", new Font(
            "Arial", Font.BOLD, 14
        ));
        UIManager.put("OptionPane.buttonFont", new Font(
            "Arial", Font.PLAIN, 12
        ));

        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        UIManager.put("OptionPane.background", new Color(19, 21, 32));
        UIManager.put("Panel.background", new Color(19, 21, 32));
        UIManager.put("ComboBox.background", new Color(19, 21, 32));

        UIManager.put("Button.background", new Color(50, 56, 78));
        UIManager.put("ComboBox.selectionBackground", new Color(50, 56, 78));
        UIManager.put("TextField.background", new Color(50, 56, 78));

        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionForeground", Color.WHITE);
        UIManager.put("TextField.foreground", Color.WHITE);

        User login = new User();
        login.login();
    }
}
