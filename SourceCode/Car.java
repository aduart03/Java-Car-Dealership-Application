import java.io.*;
import java.util.HashMap;

/**
 * Car class is the general car with common attributets.
 * Every car type inherits or overwrites the common attributtes from this class.
 * 
 * This class is an abstract class that is the blueprint for specific car types.
 */

public class Car {
    /**
     * The car ID.
     */
    private int ID;                     // The unique identifier of the car

    /**
     * The type of car.
     */
    private String carType;             // The type of the car (e.g., Sedan, SUV)

    /**
     * The model of the car.
     */
    private String model;               // The model of the car

    /**
     * The condition of the car.
     */
    private String condition;           // The condition of the car (e.g., New, Used)

    /**
     * The color of the car.
     */
    private String color;               // The color of the car

    /**
     * The capacity of the car.
     */
    private int capacity;               // The capacity of the car

    /**
     * The year of the car.
     */
    private int year;                   // The year of the car

    /**
     * The fueltype of the car.
     */
    private String fuelType;            // The fuel type of the car (e.g., Gasoline, Electric)

    /**
     * The transmission of the car.
     */
    private String transmission;        // The transmission type of the car (e.g., Manual, Automatic)

    /**
     * The VIN number of the car.
     */
    private String VIN;                 // The Vehicle Identification Number (VIN) of the car

    /**
     * The price of the car.
     */
    private double price;               // The price of the car

    /**
     * The available quantity of that type of car.
     */
    private int availableQuantity;      // The available quantity of the car

    /**
     * If the car has a turbo then true, otherwise false.
     */
    private boolean hasTurbo;

    /**
     * The revenue the car (specified by the ID) has generated.
     */
    private double carIdRevenue;

    /**
     * Constructs a new Car object with default values.
     */
    Car() {

    }

    //Setters

    /**
     * Sets the car ID.
     * @param id    The ID cat number that will set the atttribute ID to the id passed in the parameters.
     */
    public void setID(int id) {
        ID = id;
    }
    
    /**
     * Sets the car type. e.g. Sedan, SUV, Pickup, etc...
     * @param ctype  The car type that will be passed in as a string and will set this car class attribute car type to what is passed through the parameters.
     */
    public void setCarType(String ctype) {
        carType = ctype;
    }

    /**
     * Sets the model attribute of this car class to whatever is passed in to the parameters.
     * @param mod   TThe model of the car in the String form.
     */
    public void setModel(String mod) {
        model = mod;
    }

    /**
     * Sets the condition attribute of this class to whatever is passed into the parameters.
     * @param con   The condition of the car in String form.
     */
    public void setCondition(String con) {
        condition = con;
    }

    /**
     * Sets the color attribute of this class to whatever is passed into the parameters.
     * @param clr   The color of the car in String form.
     */
    public void setColor(String clr) {
        color = clr;
    }

    /**
     * Sets the capacity attribute of this class to whatever is passed into the parameters.
     * @param cap The capacity of the car in Strting form.
     */
    public void setCapacity(int cap) {
        capacity = cap;
    }

    /**
     * Sets the year attribute of this class to whatever is passed into the parameters.
     * @param yr The year of the car passed in, in integer form.
     */
    public void setYear(int yr) {
        year = yr;
    }

    /**
     * Sets the fueltype attribute of this class to whatever is passed into the parameters.
     * @param ftype The fuel type the car uses in String form.
     */
    public void setFuelType(String ftype) {
        fuelType = ftype;
    }

    /**
     * Sets the transmission attribute of this class to whatever is passed into the parameters.
     * @param tran The transmission type of the car in String form.
     */
    public void setTransmission(String tran) {
        transmission = tran;
    }

    /**
     * Sets the VIN attribute of this class to whatever is passed into the parameters.
     * @param vin The Vin number of the car passed in as a String.
     */
    public void setVIN(String vin) {
        VIN = vin;
    }

    /**
     * Sets the price attribute of this class to whatever is passed into the parameters.
     * @param p The Price of the car passed in as a tyoe double.
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * Sets the availableQuantity attribute of this class to whatever is passed into the parameters.
     * @param quant The quantity available of thet car in integer form.
     */
    public void setAvailableQuantity(int quant) {
        availableQuantity = quant;
    }

    /**
     * Sets the value of hasTurbo to a given boolean.
     * @param hT The given boolean.
     */
    public void setHasTurbo(boolean hT) {
        hasTurbo = hT;
    }

    /**
     * Setter for revenue of the car that is specified by the car ID.
     * @param rev Given value to be assigned to attribute.
     */
    public void setCarIdRevenue(double rev) {
        carIdRevenue = rev;
    }


    //Getters
    /**
     * Gets the most recent set car ID.
     * @return  The car ID attribute.
     */
    public int getID() {
        return ID;
    }

    /**
     * Gets the most recent set car type.
     * @return  The car type attribute,
     */
    public String getCarType() {
        return carType;
    }

    /**
     * Gets the most recent set model of the car.
     * @return  The model attribute of the car.
     */
    public String getModel() {
        return model;
    }

    /**
     * Gets the most recent set condition of the car.
     * @return  The condition attribute of the car.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * Gets the most recent set color of the car.
     * @return  The color atttribute of the car.
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the most recent set car capacity.
     * @return  The capacity attributte of the car.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Gets the most recent set mileage of the car.
     * @return  The mileage attribute of the car.
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets the most recent set fuel type of the car.
     * @return  The fuel type atrtibute of the car.
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * Gets the most recent set transmission of the car.
     * @return  The transmission attrtibute of the car.
     */
    public String getTransmission() {
        return transmission;
    }
    
    /**
     * Gets the most recent set VIN number of the car.
     * @return  The VIN number attribute of the car.
     */
    public String getVIN() {
        return VIN;
    }

    /**
     * Gets the most recent set price of the car.
     * @return  The price attribute of the car.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the most recent set available quantity of the car.
     * @return The availableQuantity attribute of the car.
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Gets most current value assigned to hasTurbo.
     * @return Boolean hasTurbo.
     */
    public boolean getHasTurbo() {
        return hasTurbo;
    }

    /**
     * Getter for revenue by car ID.
     * @return Current value of carIdRevenue.
     */
    public double getCarIdRevenue() {
        return carIdRevenue;
    }

    /**
     * Sets all car attributtes of the car using a provided 1D array and HashMap.
     * @param carDataArray The 1D String array containing details of a specific car.
     * @param carIndicies The HashMap which contains row indicies for car data fields
     */
    public void setAllCarAttributes(String[] carDataArray, HashMap<String, Integer> carIndicies) {
        setID(Integer.valueOf(carDataArray[carIndicies.get("ID")]));
        setCarType(carDataArray[carIndicies.get("Car Type")]);
        setModel(carDataArray[carIndicies.get("Model")]);
        setCondition(carDataArray[carIndicies.get("Condition")]);
        setColor(carDataArray[carIndicies.get("Color")]);
        setCapacity(Integer.valueOf(carDataArray[carIndicies.get("Capacity")]));
        setYear(Integer.valueOf(carDataArray[carIndicies.get("Year")]));
        setFuelType(carDataArray[carIndicies.get("Fuel Type")]);
        setTransmission(carDataArray[carIndicies.get("Transmission")]);
        setVIN(carDataArray[carIndicies.get("VIN")]);
        setPrice(Double.valueOf(carDataArray[carIndicies.get("Price")]));
        setAvailableQuantity(Integer.valueOf(carDataArray[carIndicies.get("Cars Available")]));

        // handle "Yes", "No", and ""
        if(carDataArray.length == 13) {
            String hasTurboString = carDataArray[carIndicies.get("hasTurbo")];
            if(hasTurboString.equals("Yes")) {
                setHasTurbo(true);
            }
            else {
                setHasTurbo(false);
            }
        }
        else {
            setHasTurbo(false);
        }

        setCarIdRevenue(0.00);
    }

    /**
     * Prints a single car's attributes.
     */
    public void printCarAttributes() {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.print(" | ");
        System.out.print("ID: " + ID + " | ");                    
        System.out.print("Car Type: " + carType + " | ");            
        System.out.print("Model: " + model + " | ");        
        System.out.print("Condition: " + condition + " | ");     
        System.out.print("Color: " + color + " | ");         
        System.out.print("Capacity: " + capacity + " | ");        
        System.out.print("Year: " + year + " | ");       
        System.out.print("Fuel Type: " + fuelType + " | ");        
        System.out.print("Transmission: " + transmission + " | ");      
        System.out.print("VIN: " + VIN + " | ");      
        System.out.print("Price: " + price + " | ");     
        System.out.print("Cars Available: " + availableQuantity + " | ");     
        System.out.print("Has Turbo: " + hasTurbo + " | ");       
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------");
    }

}