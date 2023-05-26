package Functions;
import Classes.Admin;
import Classes.Restaurant;
import Classes.Role;
import Classes.User;

import java.util.Scanner;

public class Functions {
    public static Scanner scanner = new Scanner(System.in) ;
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
                    makeSecurityQuestion(admin);
                    if (admin.adminRestaurantNumbers == 1){
                        Restaurant.loggedInRestaurantForAdmin = admin.adminRestaurants.get(0) ;
                    }else {
                        for (int i = 0 ; i < admin.adminRestaurantNumbers ; i++){
                            Restaurant restaurant = admin.alphabeticSortOfAdminRestaurants.get(i) ;
                            System.out.println("Restaurant Name : "+restaurant.restaurantName+"Restaurant ID : "+restaurant.restaurantID) ;
                        }
                    }
                }else {
                    System.out.println("An Admin exist with this username!");
                }
            }else if (Role.equals("USER")){
                if (isUserNameUnique(userName,Role)){
                    User user = new User(userName,password) ;
                    User.userArrayList.add(user) ;
                    System.out.println("Account created successfully");
                    makeSecurityQuestion(user);
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
    public static void ForgetPassword(String Role,String userName){
        if (Role.equals("ADMIN")){
            Admin admin = Admin.adminArrayList.get(findRoleIndex(userName,Role)) ;
            System.out.println("please answer following question to reset your password : ");
            System.out.println(admin.securityQuestion);
            String answer = scanner.nextLine() ;
            if (answer.equals(admin.getSecurityQuestionAnswer())){
                System.out.println("enter your new password : ");
                String password = scanner.nextLine() ;
                admin.setPassword(password);
            }else{
                System.out.println("your answer is incorrect!");
            }
        }else {
            User user = User.userArrayList.get(findRoleIndex(userName,Role)) ;
            System.out.println("please answer following question to reset your password : ");
            System.out.println(user.securityQuestion);
            String answer = scanner.nextLine() ;
            if (answer.equals(user.getSecurityQuestionAnswer())){
                System.out.println("enter your new password : ");
                String password = scanner.nextLine() ;
                user.setPassword(password);
            }else{
                System.out.println("your answer is incorrect!");
            }
        }
    }
    public static void makeSecurityQuestion(Role role){
        System.out.println("now please answer this security question for when you forget your password : ");
        double randomNumber = (Math.random()) * 5 ;
        int randomInt = (int) randomNumber ;
        String Question = new String("");
        String Answer ;
        if (randomInt==0){
            Question = new String("What is your favourite color ? : ") ;
        }else if (randomInt==1){
            Question = new String("What is your favourite food ? : ") ;
        }else if (randomInt==2){
            Question = new String("What is your favourite soccer team ? : ") ;
        }else if (randomInt==3){
            Question = new String("What is your favourite actor/actress ? : ") ;
        }else if (randomInt==4){
            Question = new String("What is your favourite movie ? : ") ;
        }
        System.out.println(Question);
        role.securityQuestion = new String(Question) ;
        String answer = scanner.nextLine() ;
        role.setSecurityQuestionAnswer(answer);
    }
    public static void searchRestaurant(Admin admin,String ID){
        boolean restaurantExistance = false ;
        for (int i = 0 ; i < admin.adminRestaurantNumbers ; i++){
            if (admin.adminRestaurants.get(i).restaurantID.equals(ID)){
                restaurantExistance = true ;
                Restaurant.loggedInRestaurantForAdmin = admin.adminRestaurants.get(i) ;
            }
        }
        if (restaurantExistance == false){
            System.out.println("There is no Restaurant with this ID!");
        }else {
            System.out.println("We are at panel of "+Restaurant.loggedInRestaurantForAdmin.restaurantName+" with ID of "+Restaurant.loggedInRestaurantForAdmin.restaurantID);
        }
    }
    public static void showFoodType(){
        Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
        for (int i = 0 ; i < restaurant.restaurantFoodType.size() ; i++){
            System.out.println( (i+1) +". "+restaurant.restaurantFoodType.get(i));
        }
    }
}