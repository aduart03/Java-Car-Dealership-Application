import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashMap;

/**
 * Class which implements Iterator interface
 * Specifically made to iterate through the keys (car IDs) of tickets HashMap
 */
public class TicketIterator implements Iterator<Integer> {
     /**
     * Int variable to keep track of indicies, a cursor, so to speak.
     * Initialized to 0 when TicketIterator object is first created.
     */
    int currentIndex = 0;
    
    /**
     * The ArrayList which will contain car IDs.
     */
    ArrayList<Integer> ticketArrayList;

    /**
     * Default constructor.
     */
    TicketIterator() {

    }

    /**
     * Recieves a HashMap containing car IDs as keys and ticket strings as values.
     * Stores the car IDs in an ArrayList.
     * @param tickets The tickets HashMap.
     */
    TicketIterator(HashMap<Integer, String>  tickets) {
        ticketArrayList = ticketHashToArrayList(tickets);
    }

    /**
     * Stores the keys of a given HashMap in an ArrayList.
     * @param tickets The tickets HashMap.
     * @return The ArrayList containing car IDs.
     */
    public ArrayList<Integer> ticketHashToArrayList(HashMap<Integer, String> tickets) {
        ArrayList<Integer> ticketArrayList = new ArrayList<Integer>(tickets.keySet());
        return ticketArrayList;
    }

    @Override
    /**
     * Checks if a car ID in the ArrayList is followed by another car ID.
     * @return True if the car ID is not the last item in the ArrayList.
     */
    public boolean hasNext() {
        return currentIndex < ticketArrayList.size()-1;
    }

    @Override
    /**
     * Checks if a car ID is the last item in the ArrayList (not followed by another car ID).
     * @return True if the car ID is last in the ArrayList.
     */
    public boolean onLastItem() {
        return currentIndex == ticketArrayList.size()-1;
    }

    @Override
    /**
     * Returns the next car ID in the ArrayList.
     * @return The next car ID.
     */
    public Integer next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        else {
            currentIndex = currentIndex + 1;
            return ticketArrayList.get(currentIndex);
        }
    }

    @Override
    /**
    * Returns the carID in the current index.
    * @return The current carID.
    */
    public Integer currValue() {
        return ticketArrayList.get(currentIndex);
    }


}