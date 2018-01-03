/**
 * Created by JDidat on 3/8/2016.
 */
public class ExceedsCapacityException extends Exception {
    public ExceedsCapacityException (String message) {
        super(message);
    }
    public ExceedsCapacityException () {
        super();
    }
}
