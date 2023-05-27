package Functions;
import Classes.*;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

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
                    if (admin.adminRestaurants.size() == 1){
                        Restaurant.loggedInRestaurantForAdmin = admin.adminRestaurants.get(0) ;
                    }else {
                        showRestaurantList(admin);
                    }
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
        for (int i = 0 ; i < admin.adminRestaurants.size() ; i++){
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
    public static String setID(String thingString){
        String firstchapter = new String("") ;
        String ID = new String("") ;
        int counter = 0 ;
        int randomNumber = (int) ( ((Math.random()) * 9999) + 1 ) ;
        if (thingString.equals("restaurant")){
            firstchapter = new String("R") ;
            Restaurant.counterIDRestaurant++ ;
            counter = Restaurant.counterIDRestaurant ;
        }else if (thingString.equals("food")){
            firstchapter = new String("F") ;
            Food.counterIDFood++ ;
            counter = Food.counterIDFood ;
        }else if (thingString.equals("comment")){
            firstchapter = new String("C") ;
            Comment.counterIDComment++ ;
            counter = Comment.counterIDComment ;
        }else if (thingString.equals("order")){
            firstchapter = new String("O") ;
            Order.countrIDOrder++ ;
            counter = Order.countrIDOrder ;
        }
        ID = firstchapter + randomNumber + counter ;
        return ID ;
    }
    public static void showMenu(){
        Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
        if (restaurant.restaurantMenu.size() == 0){
            System.out.println("No food added to the menu yet!");
        }else {
            for (int i = 0 ; i < restaurant.restaurantMenu.size() ; i++){
                Food food = restaurant.restaurantMenu.get(i) ;
                System.out.println("food name : "+food.foodName+" * food id : "+food.foodID+" * food cost : "+food.foodCost+" * active discount : "+food.discountActivation+" * food rate : "+food.foodRate);
            }
        }
    }
    public static void editFood(String foodID,String changingParameters,String newValue){
        Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
        Food food = new Food("",0) ;
        for (int i = 0 ; i < restaurant.restaurantMenu.size() ; i++){
            if (foodID.equals(restaurant.restaurantMenu.get(i).foodID)){
                food = restaurant.restaurantMenu.get(i) ;
            }
        }
        if (changingParameters.equals("NAME")){
            food.foodName = newValue ;
        }else if (changingParameters.equals("PRICE")){
            food.foodCost = Integer.parseInt(newValue) ;
        }else if (changingParameters.equals("DISCOUNT")){
            if (food.discountActivation){
                food.discountActivation = false ;
            }else {
                int discount = scanner.nextInt() ;
                food.discountActivation = true ;
                food.discountValue = discount ;
            }
        }
    }
    public static void addFood(String foodName,int foodCost){
        Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
        Food food = new Food(foodName,foodCost) ;
        food.foodID = setID("food") ;
        restaurant.restaurantMenu.add(food) ;
        Food.allFoodsArrayList.add(food) ;
        System.out.println("food added to menu successfully!");
    }
    public static void showRestaurantList(Admin admin){
        for (int i = 0 ; i < admin.adminRestaurants.size() ; i++){
            Restaurant restaurant = admin.adminRestaurants.get(i) ;
            System.out.println("Restaurant Name : "+restaurant.restaurantName+" , Restaurant ID : "+restaurant.restaurantID) ;
        }
    }
    public static void deleteFood(String foodID){
        Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
        boolean foodIDExistance = false , foodExistanceRestaurant = false;
        int foodIndexFood = 0 , foodIndexRestaurant = 0 ;
        for (int i = 0 ; i < Food.allFoodsArrayList.size() ; i++){
            if (Food.allFoodsArrayList.get(i).foodID.equals(foodID)){
                foodIDExistance = true ;
                foodIndexFood = i ;
            }
        }
        for (int i = 0 ; i < restaurant.restaurantMenu.size() ; i++){
            if (restaurant.restaurantMenu.get(i).foodID.equals(foodID)){
                foodExistanceRestaurant = true ;
                foodIndexRestaurant = i ;
            }
        }
        if (foodIDExistance){
            if (foodExistanceRestaurant){
                Food.allFoodsArrayList.remove(foodIndexFood) ;
                restaurant.restaurantMenu.remove(foodIndexRestaurant) ;
                System.out.println("Food deleted successfully!");
            }else {
                System.out.println("This food ID does't exist in this Restaurant!");
            }
        }else {
            System.out.println("This food ID does't exist!");
        }
    }
    public static void deactiveFood(String foodID){
        Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
        Food food = restaurant.restaurantMenu.get(0) ;
        boolean foodIDExistance = false ;
        for (int i = 0 ; i < restaurant.restaurantMenu.size() ; i++){
            if (foodID.equals(restaurant.restaurantMenu.get(i).foodID)){
                food = restaurant.restaurantMenu.get(i) ;
                foodIDExistance = true ;
            }
        }
        if (foodIDExistance){
            if (food.activeOrder){
                System.out.println("You can't disactive a food when it has active order!");
            }else {
                System.out.println("Are you sure you want to disactive this food ?");
                String answer = scanner.nextLine() ;
                if (answer.toLowerCase().equals("yes")){
                    food.possibilityOfOrdering = false ;
                    System.out.println("food disactived successfully!");
                }
            }
        }else {
            System.out.println("this foodID doesn't exist in this restaurant!");
        }
    }
}