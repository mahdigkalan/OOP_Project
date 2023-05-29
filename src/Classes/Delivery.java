package Classes;

import java.util.ArrayList;

public class Delivery extends Role {
    public static int counterIDDelivery ;
    public static ArrayList<Delivery> deliveriesArraylist = new ArrayList<Delivery>() ;
    public ArrayList<Order> deliveryOrdersHistory = new ArrayList<Order>() ;
    public Order activeOrder ;
    private String userName;
    private String password;
    public Delivery(String userName,String password){
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
