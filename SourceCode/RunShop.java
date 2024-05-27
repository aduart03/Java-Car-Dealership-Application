import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.spi.LocaleServiceProvider;
import javax.lang.model.util.ElementScanner6;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.lang.Math;

/**
 * FAQs:
 *  -   Names who are contributing to this project: Ethan Duarte, Nathan Perez, Aldo Acosta
 *  -   Date: April 29, 2024
 *  -   Course: Adv. Object-Oriented Programng(CS-3331 CRN: 25725)
 *  -   Course number: 3331
 *  -   Instructor: Dr. Bhanukiran Gurijala
 *  -   TA: Dr. Monjur Bin Shams
 *  -   Programming assignment: Project Part 3
 * 
 * Honesty Statement
 * This is entirely my teammates and my own work. We did not use any outside
 * sources including peers, experts, online sources, or the like. Only 
 * assistance from the instructor, TA, or IA will be permitted.
 * 
 * Description
 * This java file will represent a car dealership called "Mine Cars".
 * It will offer new and used cars. Thtis system shall provide wide variety
 * of different models with varying prices and mileage to satisfy customers.
 * 
 * Customers
 * Customers will have a budget to purchase cars and they can also opt for a
 * membership to recieve discounts or a better interest rate.
 * 
 * Additionally the system will ensure security by allowing users and managers
 * to sign in and gather all their information from tthe database.
 * 
 * The file path to the user data csv file should be specified as follows:
 * "path/to/user_data.csv"
 * 
 * The file path to the admin data text file should be specified as follows:
 * "path/to/admin_data.csv"
 * 
 * The file path to the logs text file should be specified as follows:
 * "path/to/logs.txt"
 * 
 * @author Ethan Duarte
 * @author Nathan Perez
 * @author Aldo Acosta
 * @version 1.0
 * @since JDK 11.0.14.1
 * 
 */

/**
 * RunShop is the driver class which contains the main method, and is where the code will execute.
 */

public class RunShop {

    /**
     * Default constructor.
     */
    RunShop() {

    }

    /**
     * Instantiates a global Scanner Reader which can be used to read inputs from the standard inout stream (i.e Strings, charac, etc..).
     * */
    static Scanner reader = new Scanner(System.in);
    
    /**
     * Creates a new global instance of Node.
     * This node can be used or accessed anywhere in the program.
     */
    static Node logs = new Node();

    /**
     * CSV file containing all the user data. This is the name of the path to the user data csv file.
     * This static string path can be accessed anywhere in the program.
     */
    static String userFileName = "user_data.csv";
    /**
     * CSV file containing all the car data. This is the name of the path to the car data csv file.
     * This static string path can be accessed anywhere in the program.
     */
    static String carFileName = "car_data.csv";

    /**
     * Creates a global new file of the user data file which can be used or called anywhere on the program.
     * This static file can be accessed anywhere in the program.
     */
    static File user_data = new File(userFileName);
    /**
     * Creates a global new file of the car data file which can be used or called anywhere in the program.
     * This static file can be accessed anywhere in the program.
     */
    static File car_data = new File(carFileName);

    /**
     * Creates a temporary file to store the modified data when deleting a row in car_data.csv
     */
    static File tempFile = new File("temp_car_data.csv");

    /**
     * A collection that stores key value pairs. This HashMap maps out the indicies of the user data csv file by collecting
     * its headers and columns so we can later use them to get key value pairs that contain the user data.
     * This static HashMap can be accessed anywhere in the program.
     */
    static HashMap<String, Integer> userDataIndicies;
    /**
     * A collection that stores key value pairs. This HashMap maps out the indicies of the car data csv file by collecting 
     * its headers and columns so we can later use them to get key value pairs that contain the car data.
     * This collection generates a type String and Integer(The key value of the pair or key of the collection).
     */
    static HashMap<String, Integer> carDataIndicies;

     /**
     * The collection of actual users which contains all the user's attributes and data.
     * This collection generates a type Integer(The key value of the pair or key of the collection) and User.
     * This static HashMap can be accessed anywhere in the program.
     */
    static HashMap<Integer, User> users;
    /**
     * The collection of actual cars which contains all the car's attributes and data.
     * This collection generates a type Integer(The key value of the pair or key of the collection) and Car.
     */
    static HashMap<Integer, Car> cars;

    /**
     * The collection of the revunue a car has generated.
     * This collection generates a type string(the car type) and double(the revenue).
     */
    static HashMap<String, Double> carTypeRevenue;
   
    /**
     * The User(type User) that is currently logged in after providing the correct username and password when prompted.
     * This user is accessible anywhere in the program.
     */
    static User userLoggedIn;
    /**
     * The Admin(type Admin) that is currently logged in after choosing the option to sign is as an administrator.
     * This admin is accessible anywhere in the program.
     */
    static Admin adminLoggedIn;

    /**
     * The new User that will be added to the user data file. This creates a new instance of that user.
     * This is user is accessible anywhere in the program.
     */
    static User newUser;
    /**
     * The new User ID that will be used to add the User to the user data file. This assigns a new user ID
     * so we dont overwrite or duplicate a user within the user data file.
     * This user ID is accessible anywhere in the program.
     */
    static int newUserID;

    /**
     * The new Car that will be added to the car data file. This creates a new instance of that car.
     * This is car is accessible anywhere in the program.
     */
    static Car newCar;
    /**
     * The new Car ID that will be used to add the Car to the car data file. This assigns a new car ID
     * so we dont overwrite or duplicate a car within the car data file.
     * This car ID is accessible anywhere in the program.
     */
    static int newCarID;

    /**
     * ID set by user when prompted so the user can remove the car from the car database (car_data.csv File).
     */
    static int removeCarID;

    /**
     * Main method which will execute the program.
     * @param args The command line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Reading user csv file and storing the data in a hash map
        userDataIndicies = createUserIndexHash(user_data);
        users = createUserObjectHash(user_data, userDataIndicies);

        // Reading car csv file and storing the data in a hash map
        carDataIndicies = createCarIndexHash(car_data);
        cars = createCarObjectHash(car_data, carDataIndicies);

        // Setting up the hashmap for revenue by car type
        carTypeRevenue = createCarTypeRevenueHash();

        // User-System interactions start here
        System.out.println("--------------------------------------------------------------------");
        displayWelcomeMenu();
    }

    /**
     * Creates a hash map containing the data fields/headers and their column indicies from user_data.csv,
     * such that the key is the data field (String), and the value is the column index (int).
     * @param file The file to be read.
     * @return The hash map containing data fields (keys) and indicies (values).
     */
    public static HashMap<String, Integer> createUserIndexHash(File file) {
        userDataIndicies = new HashMap<String, Integer>();

        try {
            Scanner reader = new Scanner (file);
            String line = reader.nextLine();
            String[] dataFields = line.split(",");
            for(int i = 0; i < dataFields.length; i++) {
                userDataIndicies.put(dataFields[i], i);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println ("File Not Found");
        }

        return userDataIndicies;
    }

    /**
     * Creates a hash map containing user objects (with each object containing the data from user_data.csv),
     * such that the key is the user's ID (int), and the value is the user object itself (User).
     * @param file The file to be read.
     * @param userDataIndicies The hash map containing the column indicies of the user data fields.
     * @return The hash map containing user IDs (keys) and user objects (values).
     */
    public static HashMap<Integer, User> createUserObjectHash(File file, HashMap<String, Integer> userDataIndicies) {
        users = new HashMap<Integer, User>();

        try {
            Scanner reader = new Scanner (file);
            reader.nextLine();
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                User user = new User();
                user.setAllUserAttributes(data, userDataIndicies);
                users.put(user.getID(), user);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println ("File Not Found");
        }

        return users;
    }

    /**
     * Creates a hash map containing the data fields/headers and their column indicies from car_data.csv,
     * such that the key is the data field (String), and the value is the column index (int).
     * @param file The file to be read.
     * @return The hash map containing data fields (keys) and indicies (values).
     */
    public static HashMap<String, Integer> createCarIndexHash(File file) {
        carDataIndicies = new HashMap<String, Integer>();

        try {
            Scanner reader = new Scanner (file);
            String line = reader.nextLine();
            String[] dataFields = line.split(",");
            for(int i = 0; i < dataFields.length; i++) {
                carDataIndicies.put(dataFields[i], i);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println ("File Not Found");
        }

        return carDataIndicies;
    }

    /**
     * Creates a hash map containing car objects (with each object containing the data from car_data.csv),
     * such that the key is the car's ID (int), and the value is the car object itself (User).
     * @param file The file to be read.
     * @param carDataIndicies The hash map containing the column indicies of the car data fields.
     * @return The hash map containing car IDs (keys) and car objects (values).
     */
    public static HashMap<Integer, Car> createCarObjectHash(File file, HashMap<String, Integer> carDataIndicies) {
        cars = new HashMap<Integer, Car>();

        try {
            Scanner reader = new Scanner (file);
            reader.nextLine();
            int i = 0;
            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split(",");
                Car car = new Car();
                car.setAllCarAttributes(data, carDataIndicies);
                cars.put(car.getID(), car);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println ("File Not Found");
        }

        return cars;
    }

    /**
     * Creates a HashMap where which the keys are car types (String) and the values are revenues (double)
     * @return the HashMap containing total revenue for each car type
     */
    public static HashMap<String, Double> createCarTypeRevenueHash() {
        HashMap<String, Double> carTypeRevenue = new HashMap<String, Double>();
        carTypeRevenue.put("sedan", 0.00);
        carTypeRevenue.put("suv", 0.00);
        carTypeRevenue.put("hatchback", 0.00);
        carTypeRevenue.put("pickup", 0.00);
        return carTypeRevenue;
    }


    /**
     * Displays the welcome menu where users have the option to login or exit.
     */
    public static void displayWelcomeMenu() {
        //  Welcome page.
        System.out.println("Welcome to Miner Cars!");
        System.out.println();
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("\nSelect an option (please enter 1 or 2).");
        System.out.println("--------------------------------------------------------------------");
        String welcomeChoice = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        handleWelcomeMenuChoice(welcomeChoice);
    }

    /**
     * Directs the system to react according to the user input in the welcome menu.
     * @param welcomeChoice The user's input from the welcome menu as a string.
     */
    public static void handleWelcomeMenuChoice(String welcomeChoice) {
        if(welcomeChoice.equals("1")) {
            displayLoginMenu();
        }
        else if(welcomeChoice.equals("2")) {
            exit();
        }
        else {
            System.out.println("Invalid input. Please enter 1 or 2.");
            System.out.println("--------------------------------------------------------------------");
            welcomeChoice = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            handleWelcomeMenuChoice(welcomeChoice);
        }
    }

    /**
     * Displays the login menu where users have the option continue as a user/customer or an administrator.
     */
    public static void displayLoginMenu() {
        System.out.println("1. Continue as user/customer");
        System.out.println("2. Continue as administrator/manager");
        System.out.println("3. Go back");
        System.out.println("\nSelect an option (please enter 1, 2, or 3).");
        System.out.println("--------------------------------------------------------------------");
        String loginChoice = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        handleLoginMenuChoice(loginChoice);
    }

    /**
     * Directs the system to react according to user input in the login menu.
     * @param loginChoice The user's input from the login menu as a string.
     */
    public static void handleLoginMenuChoice(String loginChoice) {
        if(loginChoice.equals("1")) {
            handleUsernamePasswordInput();
        }
        else if(loginChoice.equals("2")) {
            logsData("Admin logged in");
            displayAdminMenu();
        }
        else if(loginChoice.equals("3")) {
            //  Goes back to previous menu or landing page.
            displayWelcomeMenu();
        }
        else {
            //  If invalid credentials.
            System.out.println("Invalid input. Please enter 1, 2, or 3.");
            System.out.println("--------------------------------------------------------------------");
            loginChoice = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            handleLoginMenuChoice(loginChoice);
        }
    }

    /**
     * Directs the system to react according to username and password inputs. Additionally the method checks for a
     * match of the input username and password with the HashMap that contains the user data.
     */
    public static void handleUsernamePasswordInput() {
        //  Prompt user to enter username and store input.
        System.out.print("Username: ");
        String username = reader.nextLine();
        //  Prompt user to enter password and store input.
        System.out.print("Password: ");
        String password = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        boolean foundMatch = validateLogin(username, password);
        if(foundMatch) {
            System.out.println("Welcome " + username);
            System.out.println("--------------------------------------------------------------------");
            displayUserMenu();
        }
        else {
            System.out.println("Invalid username/password was entered.");
            handleInvalidCredentials();
        }
    }

    /**
     * Verifies the username and password inputs by checking if they match those found
     * in any of the stored user objects (in the hashmap).
     * @param username  The user's input string for username
     * @param password The user's input string password
     * @return true if credentials are valid, false if otherwise.
     * */
    public static boolean validateLogin(String username, String password) { 

        UserIterator userIterator = new UserIterator(users);
        User currUser = userIterator.currValue();
        while(userIterator.hasNext() || userIterator.onLastItem()) {
            if(username.equals(currUser.getUsername()) && password.equals(currUser.getPassword())) {
                userLoggedIn = currUser;
                return true;
            }

            
            if(userIterator.onLastItem()) {
                break;
            }
            else {
                currUser = userIterator.next();
            }
        }

        return false;
    }

    /**
     * Handles invalid username and password inputs.
     * If entries are invalid, the user can choose to try again or go back to the login menu.
     */
    public static void handleInvalidCredentials() {
        System.out.println("Type 1 to try again. Type 2 to go back.");
        System.out.println("--------------------------------------------------------------------");
        String input = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        if(input.equals("1")) {
            handleUsernamePasswordInput();
        }
        else if(input.equals("2")) {
            displayLoginMenu();
        }
        else {
            System.out.println("Invalid entry.");
            handleInvalidCredentials();
        }
    }

    /**
     * Prints the main User menu and prompts the user to select an option.
     */
    public static void displayUserMenu() {
        System.out.println("1. Display all cars");
        System.out.println("2. Filter cars");
        System.out.println("3. Buy a car");
        System.out.println("4. View tickets");
        System.out.println("5. Return car");
        System.out.println("6. Sign out");
        if(!userLoggedIn.getHasMembership()) {
            System.out.println("7. Opt for MinerCars Membership");
            System.out.println("\nSelect an option (please enter 1, 2, 3, 4, 5, 6, or 7)");
        }
        else {
            System.out.println("\nSelect an option (please enter 1, 2, 3, 4, 5, or 6)");
        }
        System.out.println("--------------------------------------------------------------------");
        String userMenuChoice = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        handleUserMenuChoice(userMenuChoice);
    }

    /**
     * Directs the system to react according to user input from displayUserMenu(). The options are :
     * 1. Display all cars from the car data csv file
     * 2. Filter cars by used/new
     * 3. Buy Car that exists in the car data file
     * 4. View tickets of a car if a car was bought and the cars details
     * 5. Return Car if a car was bought
     * 6. Sign Out 
     * 7. Opt For a new membership
     * @param userMenuChoice The user's input string which indicates the chosen option.
     */
    public static void handleUserMenuChoice(String userMenuChoice) {
        switch (userMenuChoice) {
            case "1":
                //  Display all cars.
                System.out.println("Displaying all cars...");
                userLoggedIn.displayCars(cars);
                logsData(userLoggedIn.getUsername() + " displayed all cars");
                displayUserMenu();
                break;
            case "2":
                // Filter cars (Used / New)
                filterByConditionPrompt();
                displayUserMenu();
                break;
            case "3":
                // Buy car
                askWhatCarToBy();
                displayUserMenu();
                break;
            case "4":
                // View tickets
                userLoggedIn.viewTickets();
                logsData(userLoggedIn.getUsername() + " viewed tickets");
                displayUserMenu();
                break;
            case "5":
                // Return car
                askWhatCarToReturn();
                displayUserMenu();
                break;
            case "6":
                logsData(userLoggedIn.getUsername() + " signed out");
                // Sign out
                signOut();
                break;
            case "7":
                // Opt for membership
                if(!userLoggedIn.getHasMembership()) {
                    System.out.println("Having a MinerCars Membership will take 10% off from your purchase.");
                    System.out.println("Would you like to opt in? (Type 'Yes' or 'No').");
                    System.out.println("--------------------------------------------------------------------");
                    String opt = reader.nextLine();
                    System.out.println("--------------------------------------------------------------------");
                    confirmOptForMembership(opt);
                }
                else {
                    System.out.println("Invalid entry. Please enter 1, 2, 3, 4, 5, or 6.");
                    System.out.println("--------------------------------------------------------------------");
                    userMenuChoice = reader.nextLine();
                    System.out.println("--------------------------------------------------------------------");
                    handleUserMenuChoice(userMenuChoice);
                }
                break;
            default:
                //  Default case if entry was invalid -> prompts user again.
                if(!userLoggedIn.getHasMembership()) {
                    System.out.println("Invalid entry. Please enter 1, 2, 3, 4, 5, 6, or 7.");
                }
                else {
                    System.out.println("Invalid entry. Please enter 1, 2, 3, 4, 5, or 6.");
                }
                System.out.println("--------------------------------------------------------------------");
                userMenuChoice = reader.nextLine();
                System.out.println("--------------------------------------------------------------------");
                handleUserMenuChoice(userMenuChoice);
                break;
        }
    }

    /**
     * Prompts the user to confirm their choice for getting a membership
     * @param opt user's input string
     */
    public static void confirmOptForMembership(String opt) {
        if(opt.equalsIgnoreCase("Yes")) {
            userLoggedIn.setHasMembership(true);
            updateCSVMemberShipStatus();
            logsData(userLoggedIn.getUsername() + " got membership");
            System.out.println("You now have the MinerCars Membership!");
            System.out.println("--------------------------------------------------------------------");
            displayUserMenu();
        }
        else if(opt.equalsIgnoreCase("No")) {
            displayUserMenu();
        }
        else {
            System.out.println("Invalid entry. Please Type 'Yes' to opt for the Membership, or type 'No' to return to the main menu.");
            System.out.println("--------------------------------------------------------------------");
            opt = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            confirmOptForMembership(opt);
        }
    }

    /**
     * Updates the user's membership status from false to true in the user csv file
     */
    public static void updateCSVMemberShipStatus() {
        int row = Integer.valueOf(userLoggedIn.getID());
        int col = userDataIndicies.get("MinerCars Membership");
        CsvFileUpdater updater = new CsvFileUpdater();
        updater.updateCSV(userFileName, row, col, "TRUE");
    }

    /**
     * This method gets the current date, time and the activity that will be logged
     * and stored as nodes in the linked list.
     * @param activity The activity or action that will be logged.
     */
    public static void logsData(String activity){

        //  Current date log.
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatterDate);
 
        //  Current Time Log.
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatterTime);

        logs.addLog(formattedDate, formattedTime, activity);
    }

    /**
     * Prompts the user to choose whether they'd like to filter cars by used or new, or go back to the main menu.
     */
    public static void filterByConditionPrompt() {
        System.out.println("Filter by: ");
        System.out.println("1. Used");
        System.out.println("2. New");
        System.out.println("3. Go back");
        System.out.println("Select an option (please enter 1, 2, or 3).");
        System.out.println("--------------------------------------------------------------------");
        String condition = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        handleFilterByConditionInput(condition);
    }

    /**
     * Directs the system to react according to the user input provided in filterByConditionPrompt().
     * @param condition the user's input string the determines the condition by which cars will be filtered.
     */
    public static void handleFilterByConditionInput(String condition) {
        if(condition.equals("1") || condition.equals("2")) {
            if(condition.equals("1")) {
                condition = "Used";
            }
            else {
                condition = "New";
            }
            HashMap<Integer, Car> filteredCars = userLoggedIn.filterCarsByCondition(cars, condition);
            System.out.println("Displaying all " + condition + " cars...");
            userLoggedIn.displayCars(filteredCars);
            logsData(userLoggedIn.getUsername() + " filtered cars");
        }
        else if(condition.equals("3")) {
            displayUserMenu();
        }
        else {
            System.out.println("Invalid entry. Please enter 1, 2, or 3.");
            System.out.println("--------------------------------------------------------------------");
            condition = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            handleFilterByConditionInput(condition);
        }
    }
    
    /**
     * Prompts the user to enter the ID of the car they'd like to purchase. This input of the ID will
     * also be used and handled by other methods per method objectives.
     */
    public static void askWhatCarToBy(){
        System.out.println("Please enter the ID of the car you'd like to purchase.");
        System.out.println("--------------------------------------------------------------------");
        String carId = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        validateCarToBuyId(carId);
    }

    /**
     * Checks if the ID that the user entered is in the car hashmap keys.
     * @param carId The user's input string for car ID.
     */
    public static void validateCarToBuyId(String carId) {
        CarIterator carIterator = new CarIterator(cars);
        Car currCar = carIterator.currValue();
        while(carIterator.hasNext() || carIterator.onLastItem()) {

            if(String.valueOf(currCar.getID()).equals(carId)) {
                Car chosenCar = currCar;
                checkIfCanBuy(chosenCar);
                return;
            }

            if(carIterator.onLastItem()) {
                break;
            }
            else {
                currCar = carIterator.next();
            }
        }
        System.out.println("Invalid entry. Please enter a valid ID, or type 'back' to return to the main menu.");
        System.out.println("--------------------------------------------------------------------");
        carId = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        if(carId.equalsIgnoreCase("back")) {
            displayUserMenu();
        }
        else {
            validateCarToBuyId(carId);
        }
    }

    /**
     * Checks if the car is in stock and if the user has enough money before going through with the purchase.
     * @param chosenCar The car chosen for purchase.
     */
    public static void checkIfCanBuy(Car chosenCar) {
        double carPrice = chosenCar.getPrice();
        double costWithTax = carPrice + (chosenCar.getPrice()*0.0625);

        if(userLoggedIn.getHasMembership()) {
            costWithTax = costWithTax - (costWithTax*0.10);
        }

        if(chosenCar.getAvailableQuantity() < 1) {
            System.out.println("Sorry, this car is out of stock.");
            System.out.println("Returning to main menu...");
            System.out.println("--------------------------------------------------------------------");
            displayUserMenu();
        }
        else if(userLoggedIn.getMoneyAvailable() < costWithTax) {
            double balance = userLoggedIn.getMoneyAvailable();
            double roundedBalance = Math.round(balance * 100.00) / 100.00;
            double roundedCarPrice = Math.round(carPrice * 100.00) / 100.00;
            double roundedCostWithTax = Math.round(costWithTax * 100.00) / 100.00;
            System.out.println("Sorry, your current balance is $" + roundedBalance + ".");
            System.out.println("You do not have enough money to purchase this car.");
            System.out.println("Car price: $" + roundedCarPrice + "");
            System.out.println("Total cost with tax: $" + roundedCostWithTax + "");
            System.out.println("Returning to main menu...");
            System.out.println("--------------------------------------------------------------------");
            displayUserMenu();
        }
        else {
            userLoggedIn.completePurchase(chosenCar, costWithTax);
            userLoggedIn.updateCarRevenues(chosenCar, costWithTax, carTypeRevenue);
            updateMoneyAvailableCSV();
            updateCarsPurchasedCSV();
            updateCarsAvailableCSV(chosenCar);
            logsData(userLoggedIn.getUsername() + " bought car");
        }
    }

    /**
     * This method updates the money available which is the representation of the the user's budget after buying a car.
     * It grabs the numeric value of money available by accessing the "Money available" collection or key value and
     * overwriting that value to the new value on the user data csv file.
     */
    public static void updateMoneyAvailableCSV() {
        // update money available
        int row = userLoggedIn.getID();
        int col = userDataIndicies.get("Money Available");
        String replace = String.valueOf(userLoggedIn.getMoneyAvailable());
        CsvFileUpdater updater = new CsvFileUpdater();
        updater.updateCSV(userFileName, row, col, replace);
    }

    /**
     * This method updates the amount of cars purchased which is represented by an int value in the user data file
     * on the 'Cars Purchased' column. It grabs the numeric value of "Cars Purchased" by accessing the "Cars Purchased"
     * collection (HashMap) or key value and overwriting that value to the new value on the user data csv file.
     */
    public static void updateCarsPurchasedCSV() {
        // update cars purchased
        int row = userLoggedIn.getID();
        int col = userDataIndicies.get("Cars Purchased");
        String replace = String.valueOf(userLoggedIn.getCarsPurchased());
        CsvFileUpdater updater = new CsvFileUpdater();
        updater.updateCSV(userFileName, row, col, replace);
    }

    /**
     * This method updates the amount of cars available which is represented by an int value in the car data file
     * ;more specifically, in thte "Cars Available" column. It grabs the the numeric value of "Cars Available" by
     * accessing the "Cars Available" collection or key value from the HashMap 'carDataIndicies' and overwriting
     * that value to the new value on the car data csv file.
     * @param chosenCar The specific car object or car type whose attribute is being updated.
     */
    public static void updateCarsAvailableCSV(Car chosenCar) {
        int row = chosenCar.getID();
        int col = carDataIndicies.get("Cars Available");
        String replace = String.valueOf(chosenCar.getAvailableQuantity());
        CsvFileUpdater updater = new CsvFileUpdater();
        updater.updateCSV(carFileName, row, col, replace);
    }

    /**
     * This method asks the user what car to return based on the ID provided by the user, and shows the car on 
     * the console aswell as the details on the car. This car returned is the car or one of the cars that this
     * user has purchased only.
     */
    public static void askWhatCarToReturn() {
        System.out.println("Please enter the ID of the car you'd like to return.");
        System.out.println("--------------------------------------------------------------------");
        String carId = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        validateCarToReturnId(carId);
    }

    /**
     * Checks if the user entered a valid car ID to return
     * @param carId The user's input string for car ID
     */
    public static void validateCarToReturnId(String carId) {
        CarIterator carIterator = new CarIterator(cars);
        Car currCar = carIterator.currValue();
        while(carIterator.hasNext() || carIterator.onLastItem()) {
            if(String.valueOf(currCar.getID()).equals(carId)) {
                Car chosenCar = currCar;
                userLoggedIn.returnCar(chosenCar);
                updateCarsAvailableCSV(chosenCar);
                updateCarsPurchasedCSV();
                updateMoneyAvailableCSV();
                logsData(userLoggedIn.getUsername() + " returned car");
                System.out.println("Your car has been returned.");
                System.out.println("Your new balance is $" + Math.round(userLoggedIn.getMoneyAvailable()*100)/100);
                System.out.println("Returning to main menu...");
                System.out.println("--------------------------------------------------------------------");
                return;
            }
    
            if(carIterator.onLastItem()) {
                break;
            }
            else {
                currCar = carIterator.next();
            }
        }
        System.out.println("Invalid entry. Please enter a valid car ID, or type 'back' to return to the main menu.");
        System.out.println("--------------------------------------------------------------------");
        carId = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        if(carId.equalsIgnoreCase("back")) {
            displayUserMenu();
        }
        else {
            validateCarToReturnId(carId);
        }
    }

    /**
     * Display's the main Admin menu. Depending on the menu choice, a case will be executed.
     */
    public static void displayAdminMenu() {
        adminLoggedIn = new Admin();
        System.out.println("1. Display all cars");
        System.out.println("2. Display all users");
        System.out.println("3. View logs");
        System.out.println("4. Add user");
        System.out.println("5. Add car");
        System.out.println("6. Remove car");
        System.out.println("7. Get revenue");
        System.out.println("8. Sign out");
        System.out.print("\nSelect an option (please enter 1, 2, 3, 4, 5, 6, 7 or 8).\n");
        System.out.println("--------------------------------------------------------------------");
        String adminMenuChoice = reader.nextLine();
        System.out.println("--------------------------------------------------------------------");
        handleAdminMenuChoice(adminMenuChoice);
    }

    /**
     * Directs the system to act according the user input provided in displayAdminMenu().
     * The menu choices are:
     * 1. Displays all the cars from the car data csv file.
     * 2. Displays all the users from the user data csv file.
     * 3. Displays all logs from the logs.txt file.
     * 4. Prompts the admin to add a new user to the user database (user data csv file).
     * 5. Prompts the admin to add a new car to the car database (car data csv file).
     * 6. Removes a car from the car database.
     * 7. Gets the revenue that a car has generated.
     * 8. Signs the admin out of the program.
     * @param adminMenuChoice User input string which indicates the chosen option.
     */
    public static void handleAdminMenuChoice(String adminMenuChoice) {
        switch(adminMenuChoice) {
            case "1":
                //  Display all cars.
                System.out.println("Displaying all cars...");
                adminLoggedIn.displayCars(cars);
                logsData("Admin displayed cars");
                displayAdminMenu();
                break;
            case "2":
                //  Display all users.
                System.out.println("Displaying all users...");
                adminLoggedIn.displayUsers(users);
                logsData("Admin displayed users");
                displayAdminMenu();
                break;
            case "3":
                // Choice to view logs as admin
                logs.displayLogs();
                System.out.println("--------------------------------------------------------------------");
                logsData("Admin viewed logs");
                displayAdminMenu();
                break;
            case "4":
                // Add user
                // New User Prompt
                newUserPrompt();
                // User is the current user -> object not hashmap. Hashmap is users.
                newUser.printUserAttributes();
                addNewUserToCSV();
                System.out.println("New user has been added. Returning to menu...");
                System.out.println("--------------------------------------------------------------------");
                logsData("Admin added new user");
                displayAdminMenu();
                break;
            case "5":
                // Add car
                // New Car Prompt
                newCarPrompt();
                // Car is the current car -> object not hashmap. Hashmap is cars.
                newCar.printCarAttributes();
                addNewCarToCSV();
                System.out.println("New car has been added. Returning to menu...");
                System.out.println("--------------------------------------------------------------------");
                logsData("Admin added new car");
                displayAdminMenu();
                break;
            case "6":
                // Remove car
                //Prompt Admin what car they want to remove based on the ID
                System.out.println("What car do you want to remove? (Specify the ID)");
                System.out.println("--------------------------------------------------------------------");
                String removeCarID = reader.nextLine();
                System.out.println("--------------------------------------------------------------------");
                removeCar(removeCarID);
                logsData("Admin removed car");
                displayAdminMenu();
                break;
            case "7":
                // Get revenue
                System.out.println("Would you like to get revenue by car ID or car type? (Please enter 'ID' or 'Type').");
                System.out.println("--------------------------------------------------------------------");
                String revenueBy = reader.nextLine();
                System.out.println("--------------------------------------------------------------------");
                getRevenueByWhat(revenueBy);
                logsData("Admin viewed car revenue");
                displayAdminMenu();
                break;
            case "8":
                //  Sign out.
                logsData("Admin signed out");
                signOut();
                break;
            default:
                //  Default case -> if entry is invalid.
                System.out.println("Invalid entry. Please enter 1, 2, 3, 4, 5, 6, 7, or 8.");
                System.out.println("--------------------------------------------------------------------");
                adminMenuChoice = reader.nextLine();
                System.out.println("--------------------------------------------------------------------");
                handleAdminMenuChoice(adminMenuChoice);
                break;
        }
    }

    /**
     * Prompts admin to add new user attributes.
     * Sets new user attributes and ID for the new user object.
     */
    public static void newUserPrompt(){

        System.out.println("Enter user details:");
                // Gather input for the new user
                System.out.print("Enter first name: ");
                String firstName = reader.nextLine();

                System.out.print("Enter last name: ");
                String lastName = reader.nextLine();

                System.out.print("Enter username: ");
                String username = reader.nextLine();

                System.out.print("Enter password: ");
                String password = reader.nextLine();

                System.out.print("Enter money available: ");
                String moneyAvailable = reader.nextLine();
                boolean isNumericalValue = ensureNumericalValue(moneyAvailable);
                while(!isNumericalValue) {
                    System.out.print("Enter money available: ");
                    moneyAvailable = reader.nextLine();
                    isNumericalValue = ensureNumericalValue(moneyAvailable);
                }

                int carsPurchased = 0;

                boolean minercarsMembership = false;
                
                // Create new user
                newUser = new User();

                // Create new ID for the new user
                newUserID = users.size() + 1;

                // Adding user to Hashmap
                users.put(newUserID, newUser);

                // Set new attributes
                newUser.setMoneyAvailable(Double.valueOf(moneyAvailable));
                newUser.setPassword(password);
                newUser.setLastName(lastName);
                newUser.setID(newUserID);
                newUser.setCarsPurchased(carsPurchased);
                newUser.setFirstName(firstName);
                newUser.setUsername(username);
                newUser.setHasMembership(minercarsMembership);

    }

    /**
     * Ensures that user input is a numerical value.
     * @param input User input string.
     * @return True if input is a numerical value, false if otherwise.
     */
    public static boolean ensureNumericalValue(String input) {
        try{
            Double.valueOf(input);
            return true;
        }
        catch(NumberFormatException exception) {
            System.out.println("You must enter a numerical value. Try again.");
            return false;
        }
    }

    /** 
     * Adds the information of the new user in a new line in the user data csv file.
     */
    public static void addNewUserToCSV() {
        //  Writes to csv file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(user_data, true))) {
            // current user is the user or user object inside the users hashmap             
            for (User currentUser : users.values()) {
                if(currentUser.getID() == newUserID){
                    writer.newLine(); // Move to the next line
                    // formatted string specifically tailored to csv file.
                    String newUserCSVData = newUser.getMoneyAvailable() + ","
                    + newUser.getPassword() + "," 
                    + newUser.getLastName() + ","
                    + newUser.getID() + "," 
                    + newUser.getCarsPurchased() + "," 
                    + newUser.getFirstName() + ","
                    + newUser.getUsername() + "," 
                    + newUser.getHasMembership();

                        // Write ro file  
                    writer.write(newUserCSVData);
                }
            }// For each loop ends
            writer.close();
        } catch (IOException e) {
            System.err.println("Error writing to user_data csv file: " + e.getMessage());
            
        }
    }

    /**
     * Prompts admin tto add car attributes.
     * Sets new car attributes and ID for the new car object.
     */
    public static void newCarPrompt(){

                //  Format: Capacity,Car Type,Cars Available,Condition,Color,ID,Year,Price,Transmission,VIN,Fuel Type,Model,hasTurbo
                //  Gather input for new car
                System.out.println("Enter car details:");
                
                System.out.print("Enter car type: ");
                String carType = reader.nextLine();

                System.out.print("Enter condition of car: ");
                String condition = reader.nextLine();

                System.out.print("Enter color of car: ");
                String carColor = reader.nextLine();

                System.out.print("Enter transmission of car: ");
                String carTransmission = reader.nextLine();

                System.out.print("Enter car VIN: ");
                String VIN = reader.nextLine();

                System.out.print("Enter car fuel type: ");
                String fuelType = reader.nextLine();

                System.out.print("Enter car model: ");
                String carModel = reader.nextLine();

                System.out.print("Enter capacity of car: ");
                String capacity = reader.nextLine();
                boolean isNumericalValue1 = ensureNumericalValue(capacity);
                while(!isNumericalValue1) {
                    System.out.print("Enter capacity of car: ");
                    capacity = reader.nextLine();
                    isNumericalValue1 = ensureNumericalValue(capacity);
                }

                System.out.print("Enter year of car: ");
                String carYear = reader.nextLine();
                boolean isNumericalValue2 = ensureNumericalValue(carYear);
                while(!isNumericalValue2) {
                    System.out.print("Enter year of car: ");
                    carYear = reader.nextLine();
                    isNumericalValue2 = ensureNumericalValue(carYear);
                }

                System.out.print("Enter cars available: ");
                String carsAvailable = reader.nextLine();
                boolean isNumericalValue3 = ensureNumericalValue(carsAvailable);
                while(!isNumericalValue3) {
                    System.out.print("Enter cars available: ");
                    carsAvailable = reader.nextLine();
                    isNumericalValue3 = ensureNumericalValue(carsAvailable);
                }

                System.out.print("Enter car price: ");
                String carPrice = reader.nextLine();
                boolean isNumericalValue4 = ensureNumericalValue(carPrice);
                while(!isNumericalValue4) {
                    System.out.print("Enter car price: ");
                    carPrice = reader.nextLine();
                    isNumericalValue4 = ensureNumericalValue(carPrice);
                }

                System.out.print("Does car have turbo? Yes or No: ");
                String carHasTurbo = reader.nextLine();
                boolean isYesOrNo = ensureYesOrNo(carHasTurbo);
                while(!isYesOrNo) {
                    System.out.print("Does car have turbo:");
                    carHasTurbo = reader.nextLine();
                    isYesOrNo = ensureYesOrNo(carHasTurbo);
                }
                if(carHasTurbo.equalsIgnoreCase("Yes")) {
                    carHasTurbo = "true";
                }
                else {
                    carHasTurbo = "false";
                }

                // Create new car
                newCar = new Car();

                // Create new ID for the new car
                newCarID = cars.size() + 1;

                // Adding car to Hashmap 
                cars.put(newCarID, newCar);

                // Set new attributes
                newCar.setCapacity(Integer.valueOf(capacity));
                newCar.setCarType(carType);
                newCar.setAvailableQuantity(Integer.valueOf(carsAvailable));
                newCar.setCondition(condition);
                newCar.setColor(carColor);
                newCar.setID(newCarID);
                newCar.setYear(Integer.valueOf(carYear));
                newCar.setPrice(Double.valueOf(carPrice));
                newCar.setTransmission(carTransmission);
                newCar.setVIN(VIN);
                newCar.setFuelType(fuelType);
                newCar.setModel(carModel);
                newCar.setHasTurbo(Boolean.valueOf(carHasTurbo));

    }

    /**
     * Ensures that user input is either 'yes' or 'no'.
     * @param input User input string.
     * @return True if input is 'yes' or 'no', false if otherwise.
     */
    public static boolean ensureYesOrNo(String input) {
        if(input.equalsIgnoreCase("Yes") || input.equalsIgnoreCase("No")) {
            return true;
        }
        else {
            System.out.println("Invalid entry. You must enter 'Yes' or 'No'. Try again.");
            return false;
        }
    }

    /**
     * Adds the information of the new car in a new line in the car data csv file.
     */
    public static void addNewCarToCSV() {
        //  Writes to csv file
        try (BufferedWriter carwriter = new BufferedWriter(new FileWriter(car_data, true))) {
            // Current car is the car or car object inside the cars hashmap
            for (Car currentCar : cars.values()) {
                if(currentCar.getID() == newCarID){
                    // Move to the next line
                    carwriter.newLine();
                    // Formatted string specifically tailored to csv file.
                    String newCarCSVData = newCar.getCapacity() + ","
                     + newCar.getCarType() + "," + newCar.getAvailableQuantity() + ","
                     + newCar.getCondition() + "," + newCar.getColor() + ","
                     + newCar.getID() + "," + newCar.getYear() + ","
                     + newCar.getPrice() + "," + newCar.getTransmission() + ","
                     + newCar.getVIN() + "," + newCar.getFuelType() + ","
                     + newCar.getModel() + "," + newCar.getHasTurbo();
                    // Write ro file
                    carwriter.write(newCarCSVData); // Write ro file  
                }
            }// For each loop ends
            carwriter.close();

        } catch (IOException e) {
            System.err.println("Error writing to car_data csv file: " + e.getMessage());
        }
    }

    /**
     * Removes specfied car by ID input by the admin when prompted, by creating a temp file and writing the cars
     * that dont have the same ID as the user input. After new file is created with updated data contents, old file
     * is destroyed and new file is renamed to old file.
     * @param removeCarID the input string of the carID being removed
     */
    public static void removeCar(String removeCarID){
        
        //car removed and car you want to remove in the future from the car data file
        Car carToRemove = new Car();
        carToRemove = getCarToRemove(removeCarID);

        while(carToRemove == null) {   
            System.out.println("Invalid entry. Please enter a valid car ID, or type 'back' to return to menu.");
            System.out.println("--------------------------------------------------------------------");
            removeCarID = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            if(removeCarID.equalsIgnoreCase("back")) {
                displayAdminMenu();
                return;
            }else {
                carToRemove = getCarToRemove(removeCarID);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(car_data));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            
            // Read car data file and write all except the "row to be deleted" into tempfile
            String firstLine;
            while((firstLine = br.readLine()) != null) {
                writer.write(firstLine);
                writer.newLine();
                break;
            }

            String line = "";
            // Reads each line of car_data
            for(Car currentCar : cars.values() ){
                //System.out.println(line);
                line = br.readLine();
                //String[] rowElements = line.split(",");
                 // Checks for id match
                 if(currentCar.getID() != Integer.valueOf(removeCarID)) {
                    // Write the line to the temporary file if it doesn't match the ID to remove
                    writer.write(line);
                    writer.newLine(); // Add newline after each line
                }else{
                    if (currentCar.getID() == Integer.valueOf(removeCarID)) {
                        // Sets the carRemoved to current car so we can show what car we removed
                        carToRemove = currentCar;
                    }
                }
                
            }// End for each loop

            // Read and write the last line from the original file
            String lastLine = "";
            while ((lastLine = br.readLine()) != null) {
                writer.write(lastLine);
                writer.newLine(); // Add newline after each line
            }// End While Loop

             // remove car from HashMap
        cars.remove(carToRemove.getID());
          
        } catch (IOException e) {
            System.err.println("Error reading/writing car_data csv file: " + e.getMessage());
            return;
        }//End Try-Catch Block

        // Replace the original file with the temporary file
        if (!car_data.delete()) {
            System.err.println("Could not delete original car_data.csv file.");
            return;
        }
        if (!tempFile.renameTo(car_data)) {
            System.err.println("Could not rename temporary file to car_data.csv.");
            return;
        }

        System.out.println("The car, model: "+ carToRemove.getModel() + ", ID: " + removeCarID + ", was successfully removed from the car database.");
        System.out.println("Returning to menu...");
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Checks if the admin's input is a valid car ID, if so, it retrieves the car object.
     * @param removeCarId Admin's input string.
     * @return The car object that is being removed
     */
    public static Car getCarToRemove(String removeCarId) {
        // check if valid ID
        CarIterator carIterator = new CarIterator(cars);
        Car currCar = carIterator.currValue();
        while(carIterator.hasNext() || carIterator.onLastItem()) {
            if(String.valueOf(currCar.getID()).equals(removeCarId)) {
                Car carToRemove = currCar;
                return carToRemove;
            }
    
            if(carIterator.onLastItem()) {
                return null;
            }
            else {
                currCar = carIterator.next();
            }
        }
        return null;
    }

    /**
     * Checks if the admin entered 'ID', 'Type', or invalid input when seeking to get revenue.
     * @param revenueBy Admin's input string.
     */
    public static void getRevenueByWhat(String revenueBy) {
        if(revenueBy.equalsIgnoreCase("ID")) {
            System.out.println("Please enter the car ID.");
            System.out.println("--------------------------------------------------------------------");
            String id = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            handleIdRevenueInput(id);

        }
        else if(revenueBy.equalsIgnoreCase("Type")) {
            System.out.println("Please enter the car type");
            System.out.println("--------------------------------------------------------------------");
            String type = reader.nextLine().toLowerCase();
            System.out.println("--------------------------------------------------------------------");
            handleTypeRevenueInput(type);
        }
        else {
            System.out.println("Invalid entry. Please enter 'ID' or 'Type', or enter 'back' to return to the menu.");
            System.out.println("--------------------------------------------------------------------");
            revenueBy = reader.nextLine();
            System.out.println("--------------------------------------------------------------------");
            if(revenueBy.equalsIgnoreCase("back")) {
                displayAdminMenu();
            }
            else {
                getRevenueByWhat(revenueBy);
            }
        }
    }

    /**
     * Ensures that the admin entered a valid car ID.
     * @param id Admin's input string.
     */
    public static void handleIdRevenueInput(String id) {
        CarIterator carIterator = new CarIterator(cars);
        Car currCar = carIterator.currValue();
        while(carIterator.hasNext() || carIterator.onLastItem()) {

            if(String.valueOf(currCar.getID()).equals(id)) {
                adminLoggedIn.getRevenueById(Integer.valueOf(id), cars);
                break;
            }

            if(carIterator.onLastItem()) {
                System.out.println("Invalid entry. Please enter a valid ID, or type 'back' to return to the menu.");
                System.out.println("--------------------------------------------------------------------");
                id = reader.nextLine();
                System.out.println("--------------------------------------------------------------------");
                if(id.equalsIgnoreCase("back")) {
                    displayAdminMenu();
                }
                else {
                    handleIdRevenueInput(id);
                }
                break;
            }
            else {
                currCar = carIterator.next();
            }
        }
    }
    
    /**
     * Ensures that the admin entered a valid car type.
     * @param type Admin's input string.
     */
    public static void handleTypeRevenueInput(String type) {
        if(type.equals("sedan") || type.equals("suv") || type.equals("hatchback") || type.equals("pickup")) {
            adminLoggedIn.getRevenueByType(type, carTypeRevenue);
        }
        else {
            System.out.println("Invalid entry. Please enter one of the valid car types (Sedan, SUV, HatchBack, Pickup), or type 'back' to return to the menu.");
            System.out.println("--------------------------------------------------------------------");
            type = reader.nextLine().toLowerCase();
            System.out.println("--------------------------------------------------------------------");
            if(type.equalsIgnoreCase("back")) {
                displayAdminMenu();
            }
            else {
                handleTypeRevenueInput(type);
            }
        }
    }

    /**
     * Signs out and returns to welcome page.
     */
    public static void signOut() {
        System.out.println("Signing out...");
        System.out.println("--------------------------------------------------------------------");
        displayWelcomeMenu();
    }

    /**
     * Exits the program and stops running.
     */
    public static void exit() {
        System.out.println("You have exited Miner Cars.");
        System.out.println("--------------------------------------------------------------------");
        System.exit(0);
    }

}