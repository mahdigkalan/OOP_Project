package Classes;

import java.util.ArrayList;

public class Food {
    public static Food selectedFood ;
    public static ArrayList<Food> allFoodsArrayList = new ArrayList<Food>() ;
    public ArrayList<Comment> foodCommentsArrayList = new ArrayList<Comment>() ;
    public ArrayList<Order> foodOrderHistory = new ArrayList<Order>() ;
    public static int counterIDFood ;
    public String foodName ;
    public String foodID ;
    public int foodCost ;
    public boolean possibilityOfOrdering ;
    public boolean discountActivation ;
    public boolean activeOrder ;
    public int discountValue ;
    public int discountTimeStampHour ;
    public int foodOrdered ;
    public int foodRatedNumber ;
    public double foodRate ;


    public Food(String foodName,int foodCost){
        this.foodName = foodName ;
        this.foodCost = foodCost ;
    }

    public void calFoodRate(){

    }
}
