package Classes;

import java.util.ArrayList;

public class User extends Role {
    public static ArrayList<User> userArrayList = new ArrayList<User>() ;
    private String userName ;
    private String password ;
    public User(String userName,String password){
        this.userName = userName ;
        this.password = password ;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
