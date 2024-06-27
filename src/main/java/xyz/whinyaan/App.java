package xyz.whinyaan;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

// Arriola
public class App {

    public static User[] userDatabase = new User[100];
    public static int userCount = 0;

    public static void start() {
        String[] options = {"Register", "Login", "Exit"};

        int choice = JOptionPane.showOptionDialog(
            null,
            "Select an option",
            "Welcome to the shop!",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);

        switch (choice) {
            case 0:
                showRegisterPanel();
                break;
            case 1:
                showLoginPanel();
                break;
            case 2:
                System.exit(0);
            default:
                start();
                break;
            }
    }

    public static void anotherTransaction() {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to make another transaction?",
                "Select an Option",
                JOptionPane.YES_NO_OPTION);

        switch (response) {
            case JOptionPane.YES_OPTION:
                SectionSelector.main();
                break;
            case JOptionPane.NO_OPTION:
                start();
                break;
            default:
                start();
                break;
        }
    }

    public static void main(String[] args) {
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);

        Color backgroundColor = new Color(19, 21, 32);
        Color secondaryBackgroundColor = new Color(50, 56, 78);
        Color foregroundColor = Color.WHITE;

        // Background
        UIManager.put("OptionPane.background", backgroundColor);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("ComboBox.background", backgroundColor);

        // Secondary Background
        UIManager.put("Button.background", secondaryBackgroundColor);
        UIManager.put("ComboBox.selectionBackground", secondaryBackgroundColor);
        UIManager.put("TextField.background", secondaryBackgroundColor);
        UIManager.put("PasswordField.background", secondaryBackgroundColor);

        // Foreground
        UIManager.put("Button.foreground", foregroundColor);
        UIManager.put("ComboBox.foreground", foregroundColor);
        UIManager.put("ComboBox.selectionForeground", foregroundColor);
        UIManager.put("TextField.foreground", foregroundColor);
        UIManager.put("TextField.foreground", foregroundColor);
        UIManager.put("PasswordField.foreground", foregroundColor);

        // Additional components
        UIManager.put("Label.foreground", foregroundColor);
        UIManager.put("CheckBox.foreground", foregroundColor);
        UIManager.put("RadioButton.foreground", foregroundColor);
        UIManager.put("TitledBorder.titleColor", foregroundColor);
        UIManager.put("ToolTip.foreground", foregroundColor);

        UIManager.put("Label.background", backgroundColor);
        UIManager.put("CheckBox.background", backgroundColor);
        UIManager.put("RadioButton.background", backgroundColor);
        UIManager.put("TitledBorder.border", BorderFactory.createLineBorder(foregroundColor));
        UIManager.put("ToolTip.background", backgroundColor);

        // Menus
        UIManager.put("Menu.background", backgroundColor);
        UIManager.put("Menu.foreground", foregroundColor);
        UIManager.put("MenuItem.background", backgroundColor);
        UIManager.put("MenuItem.foreground", foregroundColor);
        UIManager.put("PopupMenu.background", backgroundColor);
        UIManager.put("PopupMenu.foreground", foregroundColor);

        // Scrollbars
        UIManager.put("ScrollBar.background", backgroundColor);
        UIManager.put("ScrollBar.foreground", foregroundColor);
        UIManager.put("ScrollBar.thumb", secondaryBackgroundColor);
        UIManager.put("ScrollBar.track", backgroundColor);

        // Tables
        UIManager.put("Table.background", secondaryBackgroundColor);
        UIManager.put("Table.foreground", foregroundColor);
        UIManager.put("Table.selectionBackground", secondaryBackgroundColor);
        UIManager.put("Table.selectionForeground", foregroundColor);
        UIManager.put("Table.gridColor", foregroundColor);

        // Lists
        UIManager.put("List.background", backgroundColor);
        UIManager.put("List.foreground", foregroundColor);
        UIManager.put("List.selectionBackground", secondaryBackgroundColor);
        UIManager.put("List.selectionForeground", foregroundColor);

        // Trees
        UIManager.put("Tree.background", backgroundColor);
        UIManager.put("Tree.foreground", foregroundColor);
        UIManager.put("Tree.selectionBackground", secondaryBackgroundColor);
        UIManager.put("Tree.selectionForeground", foregroundColor);
        UIManager.put("Tree.textBackground", backgroundColor);
        UIManager.put("Tree.textForeground", foregroundColor);

        // initialize dummy account for testing
        // remove in deployment
        // userDatabase[userCount++] = new User(
        //     "test",
        //     "testing account",
        //     "09123456789",
        //     "test");

        start();
    }

    public static void showRegisterPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) {
            start();
        }

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(
                null,
                "Username already exists. Please try again with a different username.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            start();
        }

        String fullName = JOptionPane.showInputDialog("Enter Full Name:");
        if (fullName == null || fullName.isEmpty()) {
            start();
        }

        String contactNo = JOptionPane.showInputDialog("Enter Contact No.:");
        if (contactNo == null || contactNo.isEmpty()){
            start();
        } else if (!contactNo.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                null,
                "Contact number must contain only digits.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            start();
        } else if (!(contactNo.length() == 11)) {
            JOptionPane.showMessageDialog(
                null,
                "Contact number must only of length 11.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            start();
        }

        JPasswordField passwordField = new JPasswordField();
        int passwordOption = JOptionPane.showConfirmDialog(
            null,
            passwordField,
            "Enter Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
        if (passwordOption != JOptionPane.OK_OPTION) {
            start();
        };
        String password = new String(passwordField.getPassword());
    
        if (password.isEmpty()) {
            start();
        };
        
        JPasswordField confirmPasswordField = new JPasswordField();
        int confirmPasswordOption = JOptionPane.showConfirmDialog(
            null,
            confirmPasswordField,
            "Confirm Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
        if (confirmPasswordOption != JOptionPane.OK_OPTION) {
            start();
        }
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                null,
                "Passwords do not match!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            start();
        }

        userDatabase[userCount++] = new User(username, fullName, contactNo, password);
        JOptionPane.showMessageDialog(
                null,
                "Registration successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showLoginPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) {
            start();
        }

        JPasswordField passwordField = new JPasswordField();
        int passwordOption = JOptionPane.showConfirmDialog(
            null,
            passwordField,
            "Enter Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
        if (passwordOption != JOptionPane.OK_OPTION) {
            start();
        }
        String password = new String(passwordField.getPassword());
        if (password.isEmpty()) {
            start();
        }

        User user = getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid username or password!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Login successful! Welcome " + user.getFullName(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            SectionSelector.main();
        }
    }

    public static boolean isUsernameTaken(String username) {
        for (int i = 0; i < userCount; i++) {
            if (userDatabase[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static User getUserByUsername(String username) {
        for (int i = 0; i < userCount; i++) {
            if (userDatabase[i].getUsername().equals(username)) {
                return userDatabase[i];
            }
        }
        return null;
    }
}
