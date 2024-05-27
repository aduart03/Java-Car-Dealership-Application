import java.util.Scanner;
import java.util.HashMap;

/**
 * Sublcass of Person that is the class of the admin.
 * Admin can login and access cara data based on he menu given.
 * Admin has to be in the admin database to be able to access the menu
 * and login successfully.
 * Admin can see all log entries.
 * */
public class Admin extends Person {

    /**
     * Default Admin constructor.
     * */
    Admin() {

    }

    /**
     * Prints all the user data.
     * @param users The user HashMap.
     * */
    public void displayUsers(HashMap<Integer, User> users) {
        UserIterator userIterator = new UserIterator(users);
        User currUser = userIterator.currValue();
        while(userIterator.hasNext() || userIterator.onLastItem()) {
            currUser.printUserAttributes();
            if(userIterator.onLastItem()) {
                break;
            }
            else {
                currUser = userIterator.next();
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    /**
     * Displays the total revenue of car ID.
     * @param id The car ID.
     * @param cars The car HashMap.
     */
    public void getRevenueById(int id, HashMap<Integer, Car> cars) {
        System.out.print("Revenue for car " + id + ": ");
        System.out.println("$" + cars.get(id).getCarIdRevenue());
        System.out.println("--------------------------------------------------------------------");
    }

   /**
    * Display the total revenue of car type.
    * @param type The car type.
    * @param carTypeRevenue The HashMap of revenues by car type.
    */
    public void getRevenueByType(String type, HashMap<String, Double> carTypeRevenue) {
        System.out.print("Revenue for " + type + ": ");
        System.out.println("$" + carTypeRevenue.get(type));
        System.out.println("--------------------------------------------------------------------");
    }

}// End Admin class.