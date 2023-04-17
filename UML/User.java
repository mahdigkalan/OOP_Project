public abstract class User {
    private static User currentUser;
    private String userName, name, password, email, phoneNumber;
    private userType userType;
    private String salt;
    private int ID;

    public User(String userName,String password, String phoneNumber) {
        // initialize User object
    }

    // setter and getter methods

    public static User logToUser(String userName, String password) {
        // log to user using UserDB
    }

    public void registerUser() {
        // register user using UserDB
    }

    public static void logout() {
        // logout logged-in user
    }

    public void deleteUser() {
        // delete user using userDB
    }
}