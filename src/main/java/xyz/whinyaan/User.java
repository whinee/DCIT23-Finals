package xyz.whinyaan;

public class User {
    public String username;
    public String fullName;
    public String contactNo;
    public String password;

    public User(String username, String fullName, String contactNo,
            String password) {
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
