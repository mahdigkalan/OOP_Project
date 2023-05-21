package Functions;
import Classes.Admin;
import Classes.Role;
import Classes.User;
public class Functions {
    public static void checkPassword(String password,String Role,String userName){
        boolean numberic = false ;
        boolean capitalLetter = false ;
        boolean smallLetter = false ;
        int passwordLength = password.length() ;
        for (int i = 0 ; i < password.length() ; i++){
            int asciiCode = (int) (password.charAt(i)) ;
            if (asciiCode < 57 && asciiCode > 48){
                numberic = true ;
            }
        }
        for (int i = 0 ; i < password.length() ; i++){
            int asciiCode = (int) (password.charAt(i)) ;
            if (asciiCode < 90 && asciiCode > 65){
                capitalLetter = true ;
            }
        }
        for (int i = 0 ; i < password.length() ; i++){
            int asciiCode = (int) (password.charAt(i)) ;
            if (asciiCode < 122 && asciiCode > 97){
                smallLetter = true ;
            }
        }
        if (!numberic){
            System.out.println("Passowrd must have at least one number!");
        }
        if (!capitalLetter){
            System.out.println("Password must have at least one capital letter!");
        }
        if (!smallLetter){
            System.out.println("Password must have at least one small letter!");
        }
        if (passwordLength < 8){
            System.out.println("Password must have at least 8 charecter!");
        }
        if (numberic && capitalLetter && smallLetter && passwordLength >= 8){
            if (Role.equals("ADMIN")){
                if (isUserNameUnique(userName,Role)){
                    Admin admin = new Admin(userName,password) ;
                    Admin.adminArrayList.add(admin) ;
                    System.out.println("Account created successfully");
                }else {
                    System.out.println("An Admin exist with this username!");
                }
            }else if (Role.equals("USER")){
                if (isUserNameUnique(userName,Role)){
                    User user = new User(userName,password) ;
                    User.userArrayList.add(user) ;
                    System.out.println("Account created successfully");
                }else {
                    System.out.println("A User exist with this username!");
                }
            }
        }
    }
    public static void LogIn(String password,String Role,String userName){
        if (Role.equals("ADMIN")){
            if (isUserNameUnique(userName,Role)){
               System.out.println("This username does not exist!");
            }else {
                Admin admin = Admin.adminArrayList.get(findRoleIndex(userName,Role)) ;
                if (admin.getPassword().equals(password)){
                    Classes.Role.loggedInRole = admin ;
                    Classes.Role.loggedInRoleExistance = true ;
                    System.out.println("you are logged in as ADMIN!");
                }else {
                    System.out.println("password is incorrect!");
                }
            }
        }else if (Role.equals("USER")){
            if (isUserNameUnique(userName,Role)){
                System.out.println("This username does not exist!");
            }else {
                User user = User.userArrayList.get(findRoleIndex(userName,Role)) ;
                if (user.getPassword().equals(password)){
                    Classes.Role.loggedInRole = user ;
                    Classes.Role.loggedInRoleExistance = true ;
                    System.out.println("you are logged in as USER!");
                }else {
                    System.out.println("password is incorrect!");
                }
            }
        }
    }
    public static boolean isUserNameUnique(String UserName,String Role){
        boolean isUserNameUnique = true ;
        if (Role.equals("ADMIN")){
            for (int i = 0 ; i < Admin.adminArrayList.size() ; i++){
                if (Admin.adminArrayList.get(i).getUserName().equals(UserName)){
                    isUserNameUnique = false ;
                }
            }
        }else if (Role.equals("USER")){
            for (int i = 0 ; i < User.userArrayList.size() ; i++){
                if (User.userArrayList.get(i).getUserName().equals(UserName)){
                    isUserNameUnique = false ;
                }
            }
        }
        return isUserNameUnique ;
    }
    public static int findRoleIndex(String userName,String Role){
        int index = 0 ;
        if (Role.equals("ADMIN")){
            for (int i = 0 ; i < Admin.adminArrayList.size() ; i++){
                if (Admin.adminArrayList.get(i).getUserName().equals(userName)){
                    index = i ;
                }
            }
        }else if (Role.equals("USER")){
            for (int i = 0 ; i < User.userArrayList.size() ; i++){
                if (User.userArrayList.get(i).getUserName().equals(userName)){
                    index = i ;
                }
            }
        }
        return index ;
    }
}