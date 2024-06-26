package xyz.whinyaan;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

// Arriola
public class App {

    public static User[] userDatabase = new User[100];
    public static int userCount = 0;

    public static void start() throws IllegalStateException {
        String[] options = {"Register", "Login", "Exit"};

        while (true) {
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
                    break;
            }
        }
    }

    public void anotherTransaction() throws IllegalStateException {
        int response = JOptionPane.showConfirmDialog(
                null,
                "Do you want to make another transaction?",
                "Select an Option",
                JOptionPane.YES_NO_OPTION);

        switch (response) {
            case JOptionPane.YES_OPTION:
                Section section = new Section();
                section.selectSection();
                break;
            case JOptionPane.NO_OPTION:
                start();
                break;
            default:
                start();
                break;
        }
    }

    public static void main(String[] args) throws IllegalStateException {
        UIManager.put("OptionPane.messageFont", new Font(
                "Arial", Font.BOLD, 14));
        UIManager.put("OptionPane.buttonFont", new Font(
                "Arial", Font.PLAIN, 12));

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

        // initialize dummy account for testing
        // remove in deployment
        userDatabase[userCount++] = new User(
            "test",
            "testing account",
            "09123456789",
            "test");

        start();
    }

    public static void showRegisterPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) {
            return;
        }

        String fullName = JOptionPane.showInputDialog("Enter Full Name:");
        if (fullName == null || fullName.isEmpty()) {
            return;
        }

        String contactNo = JOptionPane.showInputDialog("Enter Contact No.:");
        if (contactNo == null || !contactNo.matches("\\d+")) {
            JOptionPane.showMessageDialog(
                null,
                "Contact number must contain only digits!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPasswordField passwordField = new JPasswordField();
        int passwordOption = JOptionPane.showConfirmDialog(
            null,
            passwordField,
            "Enter Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
        if (passwordOption != JOptionPane.OK_OPTION) return;
        String password = new String(passwordField.getPassword());
        if (password.isEmpty()) return;
        
        JPasswordField confirmPasswordField = new JPasswordField();
        int confirmPasswordOption = JOptionPane.showConfirmDialog(
            null,
            confirmPasswordField,
            "Confirm Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
        if (confirmPasswordOption != JOptionPane.OK_OPTION) return;
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(
                null,
                "Username already exists!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        userDatabase[userCount++] = new User(username, fullName, contactNo, password);
        JOptionPane.showMessageDialog(
                null,
                "Registration successful!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showLoginPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) {
            return;
        }

        JPasswordField passwordField = new JPasswordField();
        int passwordOption = JOptionPane.showConfirmDialog(
            null,
            passwordField,
            "Enter Password:",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);
        if (passwordOption != JOptionPane.OK_OPTION) return;
        String password = new String(passwordField.getPassword());
        if (password.isEmpty()) return;

        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Login successful! Welcome " + user.getFullName(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            Section section = new Section();
            section.selectSection();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid username or password!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
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
