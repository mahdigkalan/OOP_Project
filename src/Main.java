import Classes.Admin;
import Classes.Restaurant;
import Classes.Role;
import Functions.Functions;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in) ;
        boolean inputValidation = true ;
        while (inputValidation){
            String input = scanner.nextLine() ;
            if (input.equals("end")){
                inputValidation = false ;
                break ;
            }else {
                String[] inputArray = input.split(" ") ;
                if (inputArray[0].equals("ADD") && inputArray.length == 4){
                    if (!inputArray[1].equals("ADMIN") && !inputArray[1].equals("USER")){
                        System.out.println("Choosen Role is Invalid!");
                    }else {
                        Functions.checkPassword(inputArray[3],inputArray[1],inputArray[2]) ;
                    }
                }else if (inputArray[0].equals("LOGIN") && inputArray.length == 4){
                    Functions.LogIn(inputArray[3],inputArray[1],inputArray[2]) ;
                }else if (input.equals("LOGOUT")){
                    if (Role.loggedInRoleExistance){
                        Role.loggedInRole = null ;
                        Role.loggedInRoleExistance = false ;
                        System.out.println("Logged out sucessfully!");
                    }else {
                        System.out.println("There is no LoggedIn account!");
                    }
                }else if(inputArray[0].equals("FORGET") &&inputArray[1].equals("PASSWORD") && inputArray.length == 4){
                    String Role = inputArray[2] ;
                    String userName = inputArray[3] ;
                    Functions.ForgetPassword(Role,userName);
                }else if (inputArray[0].equals("SELECT") && inputArray.length == 2 && Role.loggedInRoleExistance && Role.loggedInRole instanceof Admin){
                    Admin admin = (Admin) Role.loggedInRole ;
                    Functions.searchRestaurant(admin,inputArray[1]);
                }else if (inputArray[0].equals("ADD") && inputArray[2].equals("RESTAURANT") && inputArray.length == 2 && Role.loggedInRoleExistance && Role.loggedInRole instanceof Admin){
                    Admin admin = (Admin) Role.loggedInRole ;
                    ///////////////////////////////////


                    //////////////////////////////////
                }else if (inputArray[0].equals("SHOW") && inputArray[1].equals("FOODTYPE") && inputArray.length ==2 && Role.loggedInRoleExistance && Role.loggedInRole instanceof Admin && Restaurant.loggedInRestaurantForAdmin != null){
                    Functions.showFoodType();
                }else if (inputArray[0].equals("ADD") && inputArray[1].equals("FOODTYPE") && inputArray.length ==2 && Role.loggedInRoleExistance && Role.loggedInRole instanceof Admin && Restaurant.loggedInRestaurantForAdmin != null){
                    Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
                    System.out.println("Add number of foodtypes you want to add : ");
                    int numberOfFoodTypes = scanner.nextInt() ;
                    for (int i = 0 ; i < numberOfFoodTypes ; i++){
                        String foodtype = scanner.nextLine() ;
                        restaurant.restaurantFoodType.add(foodtype) ;
                    }
                }else if (inputArray[0].equals("EDIT") && inputArray[1].equals("FOODTYPE") && Role.loggedInRoleExistance && Role.loggedInRole instanceof Admin && Restaurant.loggedInRestaurantForAdmin != null){
                    Restaurant restaurant = Restaurant.loggedInRestaurantForAdmin ;
                    if (restaurant.restaurantOrders.size() > 0){
                        System.out.println("You can't edit food type when restaurant has active order!");
                    }else {
                        System.out.println("ARE YOU SURE YOU WANT TO CHANGE YOUR RESTAURANT TYPE? (enter yes or no)");
                        String answer = scanner.nextLine() ;
                        if (answer.toLowerCase().equals("yes")){
                            restaurant.restaurantMenu.clear();
                            restaurant.restaurantFoodType.clear();
                            for (int i = 0 ; i < inputArray.length-2 ; i++){
                                restaurant.restaurantFoodType.add(inputArray[i+2]) ;
                            }
                            System.out.println("food type edited succesfully , now you have to recreate your restaurant menu!");
                        }
                    }
                }else if (inputArray[0].equals("SELECT") && inputArray[1].equals("MENU") && Role.loggedInRoleExistance && Role.loggedInRole instanceof Admin && Restaurant.loggedInRestaurantForAdmin != null){
                    Functions.showMenu();
                }
                else {
                    System.out.println("invalid command");
                }
            }
        }
    }
}