package xyz.whinyaan;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Color;

// Arriola
public class App {
    public static User[] userDatabase = new User[100];
    public static int userCount = 1;

    public static void start() throws IllegalStateException {
        String[] options = {"Register", "Login", "Exit"};

        while (true) {
            int choice = JOptionPane.showOptionDialog(null, "Select an option", "Welcome to the shop!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                showRegisterPanel();
            } else if (choice == 1) {
                showLoginPanel();
            } else if (choice == 2) {
                System.exit(0);
            }
        }
    }

    public void anotherTransaction() throws IllegalStateException
    {
        int response = JOptionPane.showConfirmDialog(
            null,
            "Do you want to make another transaction?",
            "Select an Option",
            JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            Section section = new Section();
            section.selectSection();
        } else if (response == JOptionPane.NO_OPTION) {
            start();
        } else {
            start();
        }
    }

    public static void main(String[] args) throws IllegalStateException {
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

        // initialize dummy account for testing
        // remove in deployment
        userDatabase[userCount++] = new User(username, fullName, contactNo, password)

        start();
    }

    public static void showRegisterPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) return;
        
        String fullName = JOptionPane.showInputDialog("Enter Full Name:");
        if (fullName == null || fullName.isEmpty()) return;
        
        String contactNo = JOptionPane.showInputDialog("Enter Contact No.:");
        if (contactNo == null || !contactNo.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Contact number must contain only digits!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String password = JOptionPane.showInputDialog("Enter Password:");
        if (password == null || password.isEmpty()) return;
        
        String confirmPassword = JOptionPane.showInputDialog("Confirm Password:");
        if (confirmPassword == null || !password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(null, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        userDatabase[userCount++] = new User(username, fullName, contactNo, password);
        JOptionPane.showMessageDialog(null, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showLoginPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) return;
        
        String password = JOptionPane.showInputDialog("Enter Password:");
        if (password == null || password.isEmpty()) return;

        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + user.getFullName(), "Success", JOptionPane.INFORMATION_MESSAGE);
            Section section = new Section();
            section.selectSection();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
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
