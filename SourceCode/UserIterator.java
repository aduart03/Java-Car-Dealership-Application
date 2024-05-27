import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashMap;

/**
 * Class which implements iterator interface
 * Specifically made for iterating through user objects
 */
public class UserIterator implements Iterator<User> {
     /**
     * Int variable to keep track of indicies, a cursor, so to speak.
     * Initialized to 0 when UserIterator object is first created.
     */
    int currentIndex = 0;
    
    /**
     * The ArrayList which will contain user objects.
     */
    ArrayList<User> userArrayList;

    /**
     * Default constructor.
     */
    UserIterator() {

    }

    /**
     * Recieves a HashMap containing user objects as values and stores them in an ArrayList.
     * @param users The HashMap of user objects.
     */
    UserIterator(HashMap<Integer, User>  users) {
        userArrayList = userHashToArrayList(users);
    }


    /**
     * Stores user objects in a given HashMap in an ArrayList.
     * @param users The HashMap of user objects.
     * @return The ArrayList of user objects.
     */
    public ArrayList<User> userHashToArrayList(HashMap<Integer, User> users) {
        ArrayList<User> userArrayList = new ArrayList<User>(users.values());
        return userArrayList;
    }

    @Override
    /**
     * Checks if a user object in the ArrayList is followed by another user object.
     * @return True if the user object is not the last item in the ArrayList.
     */
    public boolean hasNext() {
        return currentIndex < userArrayList.size()-1;
    }

    @Override
    /**
     * Checks if a user object is the last item in the ArrayList (not followed by another user object).
     * @return True if the user object is last in the ArrayList.
     */
    public boolean onLastItem() {
        return currentIndex == userArrayList.size()-1;
    }

    @Override
    /**
     * Returns the next user object in the ArrayList.
     * @return The next user object.
     */
    public User next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        else {
            currentIndex = currentIndex + 1;
            return userArrayList.get(currentIndex);
        }
    }

    @Override
    /**
     * Returns the user object in the current index.
     * @return The current user object.
     */
    public User currValue() {
        return userArrayList.get(currentIndex);
    }

}