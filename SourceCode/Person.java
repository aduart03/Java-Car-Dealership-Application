import java.util.HashMap;

/**
 * Abstract class which acts as a superclass for Admin and User
 */
public abstract class Person {

    /**
     * Default constructor.
     */
    Person() {

    }

    /**
     * Prints all car data read from a HashMap given.
     * @param cars The  HashMap which contains car objects.
     */
    public void displayCars(HashMap<Integer, Car> cars) {
        CarIterator carIterator = new CarIterator(cars);
        Car currCar = carIterator.currValue();
        while(carIterator.hasNext() || carIterator.onLastItem()) {
            currCar.printCarAttributes();
            if(carIterator.onLastItem()) {
                break;
            }
            else {
                currCar = carIterator.next();
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------");
    }
}