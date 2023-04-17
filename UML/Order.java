import java.util.ArrayList;

public class Order {
    private Customer customer;
    private Resturant resturant;
    private long price;
    private ArrayList<Food> foods;
    private int status; // cooking, delivering, complete, cancel

    public Order() {
        //
    }

    // getter and setter methods

    public void sendToDB() {
        // send to DB using OrderDB
    }

    public void updateInDB() {
        // update order in DB
    }

}
