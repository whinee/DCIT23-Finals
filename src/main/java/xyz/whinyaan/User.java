package xyz.whinyaan;

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
