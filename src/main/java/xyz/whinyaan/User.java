package xyz.whinyaan;

import javax.swing.JOptionPane;

public class ShopApplication {
    private static User[] userDatabase = new User[100];
    private static int userCount = 0;

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Register", "Login", "Exit"};
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

    private static void showRegisterPanel() {
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

    private static void showLoginPanel() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        if (username == null || username.isEmpty()) return;
        
        String password = JOptionPane.showInputDialog("Enter Password:");
        if (password == null || password.isEmpty()) return;

        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Login successful! Welcome " + user.getFullName(), "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static boolean isUsernameTaken(String username) {
        for (int i = 0; i < userCount; i++) {
            if (userDatabase[i].getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private static User getUserByUsername(String username) {
        for (int i = 0; i < userCount; i++) {
            if (userDatabase[i].getUsername().equals(username)) {
                return userDatabase[i];
            }
        }
        return null;
    }
}

class User {
    private String username;
    private String fullName;
    private String contactNo;
    private String password;

    public User(String username, String fullName, String contactNo, String password) {
        this.username = username;
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getPassword() {
        return password;
    }
}
