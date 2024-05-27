import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashMap;

/**
 * Class which implements iterator interface
 * Specifically made for iterating through car objects
 */
public class CarIterator implements Iterator<Car> {
    /**
     * int variable to keep track of indicies, a cursor, so to speak
     * Initialized to 0 when CarIterator object is first created
     */
    int currentIndex = 0;

    /**
     * The ArrayList which will contain car objects
     */
    ArrayList<Car> carArrayList;

    /**
     * default constructor
     */
    CarIterator() {

    }

    /**
     * Recieves a HashMap containing car objects as values and stores them in an ArrayList
     * @param cars the HashMap of car objects
     */
    CarIterator(HashMap<Integer, Car>  cars) {
        carArrayList = carHashToArrayList(cars);
    }

    /**
     * Stores car objects in a given HashMap in an ArrayList
     * @param cars the HashMap of car objects
     * @return the ArrayList of car objects
     */
    public ArrayList<Car> carHashToArrayList(HashMap<Integer, Car> cars) {
        ArrayList<Car> carArrayList = new ArrayList<Car>(cars.values());
        return carArrayList;
    }

    @Override
    /**
     * Checks if a car object in the ArrayList is followed by another car object
     * @return true if the car object is not the last item in the ArrayList
     */
    public boolean hasNext() {
        return currentIndex < carArrayList.size()-1;
    }

    @Override
    /**
     * Checks if a car object is the last item in the ArrayList (not followed by another car object)
     * @return true if the car object is last in the ArrayList
     */
    public boolean onLastItem() {
        return currentIndex == carArrayList.size()-1;
    }

    @Override
    /**
     * Returns the next car object in the ArrayList
     * @return the next car object
     */
    public Car next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        else {
            currentIndex = currentIndex + 1;
            return carArrayList.get(currentIndex);
        }
    }

    @Override
    /**
     * Returns the car object in the current index
     * @return the current car object
     */
    public Car currValue() {
        return carArrayList.get(currentIndex);
    }

}