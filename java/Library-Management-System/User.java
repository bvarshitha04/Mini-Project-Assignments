public class User {
    private int userId;
    private String fullName;
    private String contactNumber;

    public User(int userId, String fullName, String contactNumber) {
        this.userId = userId;
        this.fullName = fullName;
        this.contactNumber = contactNumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
