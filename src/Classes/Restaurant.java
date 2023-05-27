package Classes;

import java.util.ArrayList;

public class Restaurant {
    public static Restaurant loggedInRestaurantForAdmin ;
    public static int counterIDRestaurant ;
    public ArrayList<String> restaurantFoodType = new ArrayList<String>() ;
    public ArrayList<Order> restaurantOrders = new ArrayList<Order>() ;
    public ArrayList<Food> restaurantMenu = new ArrayList<Food>() ;
    public String restaurantID ;
    public String restaurantName ;
    public Admin restaurantOwner ;
    public String restaurantLocation ;
    public Restaurant(String restaurantName){
        this.restaurantName = restaurantName ;
    }
}
