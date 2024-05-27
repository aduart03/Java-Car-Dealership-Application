import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.lang.Math;

/**
 * Sublcass of Person that is the class of the user.
 * User can login and access cara data based on he menu given.
 * User has to be in the user database to be able to access the menu
 * and login successfully.
 */

public class User extends Person {
    static Scanner reader  = new Scanner(System.in);

    /**
     * The user ID.
     */
    private int ID;                 //  The unique identifier of the car.

    /**
     * The user's Firstname.
     */
    private String firstName;       //  The unique first name of the user.

    /**
     * The user's Lastname.
     */
    private String lastName;        //  The unique last name of the user.

    /**
     * The user's budget.
     */
    private double moneyAvailable;  //  The funds availble of the user.

    /**
     * The amount of cars the user has purchased.
     */
    private int carsPurchased;      //  The cars purchased by the user.

    /**
     * True if the user has a membership, otherwise false.
     */
    private boolean hasMembership;   // true if the user has a membership, false if not.

    /**
     * The user's username.
     */
    private String username;        //  The user's username 

    /**
     * The user's password.
     */
    private String password;        //  The user's password

    /**
     * The tickets the user recieves after purchasing a car.
     */
    HashMap<Integer, String> tickets;       //  The tickets of the user based on their purchase.

    /**
     * User default constructor.
     */
    User() {

    }

    /**
     * Sets the ID of the User.
     * @param id    The ID of the user in integer form.
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Sets the First name of the user.
     * @param fn    The first name that will be set for the user in String form.
     */
    public void setFirstName(String fn) {
        firstName = fn;
    }

    /**
     * Sets the Last name of the user.
     * @param ln    The last name that will be set for the user in String form.
     */
    public void setLastName(String ln) {
        lastName = ln;
    }

    /**
     * Sets the money available the user will have.
     * @param ma    Amount of money that will be set avaialbe in double form.
     */
    public void setMoneyAvailable(double ma) {
        moneyAvailable = ma;
    }

    /**
     * Sets the cars purchased.
     * @param cp    Cars purchased as integer value.
     */
    public void setCarsPurchased(int cp) {
        carsPurchased = cp;
    }
    
    /**
     * Sets the membership of the user.
     * @param hm    The membership of the user in String form.
     */
    public void setHasMembership(boolean hm) {
        hasMembership = hm;
    }

    /**
     * Sets the user/admin username in String form
     * @param un    The String that will be the username.
     */
    public void setUsername(String un) {
        username = un;
    }

    /**
     * Sets the user/admin password in String form.
     * @param pw    The String that will be the password.
     */
    public void setPassword(String pw) {
        password = pw;
    }

    /**
     * Gets the most recent set ID of the user.
     * @return  The user ID.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the most recent set First name of the user.
     * @return  The user First name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the most recent set Last name of the user.
     * @return  The user Last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the most recent set money available of the user.
     * @return The user's available funds.
     */
    public double getMoneyAvailable() {
        return moneyAvailable;
    }

    /**
     * Gets the most recent set cars purchased by the user.
     * @return  The cars purchased by the user.
     */
    public int getCarsPurchased() {
        return carsPurchased;
    }
    
    /**
     * Gets the most recent set membership of the user.
     * @return  The membership of the user.
     */
    public boolean getHasMembership() {
        return hasMembership;
    }

    /**
     * Gets the user/admin username.
     * @return  The user/admin username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets teh user/admin password.
     * @return  The user/admin password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the user's atttributes.
     * @param userDataArray The array that contains the user data that will be used to set the attributes of the user.
     * @param userIndicies The HashMap that contains the column index of each data field.
     * */
    public void setAllUserAttributes(String[] userDataArray, HashMap<String, Integer> userIndicies) {
        setID(Integer.valueOf(userDataArray[userIndicies.get("ID")]));
        setFirstName(userDataArray[userIndicies.get("First Name")]);
        setLastName(userDataArray[userIndicies.get("Last Name")]);
        setMoneyAvailable(Double.valueOf(userDataArray[userIndicies.get("Money Available")]));
        setCarsPurchased(Integer.valueOf(userDataArray[userIndicies.get("Cars Purchased")]));
        setHasMembership(Boolean.valueOf(userDataArray[userIndicies.get("MinerCars Membership")]));
        setUsername(userDataArray[userIndicies.get("Username")]);
        setPassword(userDataArray[userIndicies.get("Password")]);
        tickets = new HashMap<Integer, String>();
    }

    /**
     * Prints a single user's attributes.
     */
    public void printUserAttributes() {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.print(" | ");
        System.out.print("ID: " + ID + " | ");                    
        System.out.print("First Name: " + firstName + " | ");            
        System.out.print("Last Name: " + lastName + " | ");        
        System.out.print("Money Available: " + moneyAvailable + " | ");     
        System.out.print("Cars Purchased: " + carsPurchased + " | ");         
        System.out.print("MinerCars Membership: " + hasMembership + " | ");        
        System.out.print("Username: " + username + " | ");       
        System.out.print("Password: " + password + " | ");        
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    /**
     * Filters car objects by used or new.
     * @param cars The hashmap which contains car objects.
     * @param condition The the string that says "Used" or "New".
     * @return The hashmap containing all used cars or all new cars.
     **/
    public HashMap<Integer, Car> filterCarsByCondition(HashMap<Integer, Car> cars, String condition) {
        HashMap<Integer, Car> filteredCars = new HashMap<Integer, Car>();

        CarIterator carIterator = new CarIterator(cars);
        Car currCar = carIterator.currValue();
        while(carIterator.hasNext() || carIterator.onLastItem()) {
            if(currCar.getCondition().equals(condition)) {
                filteredCars.put(currCar.getID(), currCar);
            }

            if(carIterator.onLastItem()) {
                break;
            }
            else {
                currCar = carIterator.next();
            }
        }

        return filteredCars;
    }


    /**
     * Completes the purchase of a car.
     * @param chosenCar The car that the user has chosen to purchase.
     * @param costWithTax  The total cost (including tax) that the user will pay to purchase the car.
     * */
    public void completePurchase(Car chosenCar, double costWithTax) {
        // create and display new ticket
        String newTicket = createTicket(chosenCar);
        tickets.put(chosenCar.getID(), newTicket);
        System.out.println("Ticket: " + newTicket);
        System.out.println("Car price: $" + Math.round(chosenCar.getPrice() * 100.00) / 100.00);

        // update user and car objects 

        // update user's amount of money available
        setMoneyAvailable((getMoneyAvailable() - costWithTax));
        // update user's number of cars purchased
        setCarsPurchased(getCarsPurchased()+1);
        // update car's available quantity
        chosenCar.setAvailableQuantity(chosenCar.getAvailableQuantity()-1);

        // Print check out information and confirm purchase
        if(getHasMembership()) {
            System.out.println("10% discount was added.");
        }
        System.out.println("Total cost with tax: $" + Math.round(costWithTax * 100.00) / 100.00);
        System.out.println("Remaining balance: $" + Math.round(getMoneyAvailable() * 100.00) / 100.00);
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Updates the total revenues of car ID and car type after a purchase
     * @param chosenCar the car object of the car being purchased
     * @param costOfPurchase the total amount of money that the user purchased the car for
     * @param carTypeRevenue the hashmap containing current revenues for each car type
     */
    public void updateCarRevenues(Car chosenCar, double costOfPurchase, HashMap<String, Double> carTypeRevenue) {
        chosenCar.setCarIdRevenue(costOfPurchase);

        String typeOfCar = chosenCar.getCarType().toLowerCase();
        double currTypeRevenue = carTypeRevenue.get(typeOfCar);
        carTypeRevenue.remove(typeOfCar);
        double newTypeRevenue = currTypeRevenue + costOfPurchase;
        carTypeRevenue.put(typeOfCar, newTypeRevenue);
    }

    /**
     * Creates a ticket for a single car purchase.
     * @param chosenCar The car being purchased.
     * @return The string which is the ticket itself.
     * */
    public String createTicket(Car chosenCar) {
        // Ticket based on car.
        String ticket = "| Car ID: " + chosenCar.getID() + 
                        " | Car Type: " + chosenCar.getCarType() + 
                        " | " + "Model: " + chosenCar.getModel() + 
                        " | " + "Year: " + chosenCar.getYear() + 
                        " | " + "Color: " + chosenCar.getColor() + " |";
        return ticket;
    }

    /**
     * Prints all of the user's tickets.
     * */
    public void viewTickets() {
        //  Displays all the user's tickets.
        TicketIterator ticketIterator = new TicketIterator(tickets);
        if(tickets.isEmpty() || ticketIterator.ticketArrayList.isEmpty()) {
            System.out.println("You have no tickets at this time.");
        }
        else {
            System.out.println("Displaying tickets...");
            Integer currCarId = ticketIterator.currValue();
            while(ticketIterator.hasNext() || ticketIterator.onLastItem()) {
                System.out.println(tickets.get(currCarId));
                if(ticketIterator.onLastItem()) {
                    break;
                }
                else {
                    currCarId = ticketIterator.next();
                }
            }
        }
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Updates car's quantitiy, user's money available, and cars purchased when returning a car
     * @param chosenCar the car object of the car being returned
     */
    public void returnCar(Car chosenCar) {
        chosenCar.setAvailableQuantity(chosenCar.getAvailableQuantity()+1);

        setCarsPurchased(getCarsPurchased()-1);

        double moneyback = chosenCar.getPrice() + (chosenCar.getPrice()*0.0625);
        if(getHasMembership()) {
            moneyback = moneyback - (moneyback*0.10);
        }
        setMoneyAvailable(getMoneyAvailable()+moneyback);
    }

}