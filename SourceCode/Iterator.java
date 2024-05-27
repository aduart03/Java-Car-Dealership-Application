import java.util.ArrayList;

/**
 * Interface for custom iterator classes.
 */
public interface Iterator<I> {

    /**
     * Checks if an item in a list is followed by another item.
     * @return true if an item has another item after it.
     */
    public abstract boolean hasNext();

    /**
     * Checks if an item is the last item in the list (followed by no other item).
     * @return True if an item is the last item.
     */
    public abstract boolean onLastItem();

    /**
     * Returns the the next item on the list.
     * @return The next item.
     */
    public abstract I next();

    /**
     * Returns the item in the current index.
     * @return The current value of the item.
     */
    public abstract I currValue();

}