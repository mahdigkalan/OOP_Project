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
                }
                else {
                    System.out.println("invalid command");
                }
            }
        }
    }
}