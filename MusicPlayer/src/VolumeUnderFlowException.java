/**
 * Created by JDidat on 3/6/2016.
 */
public class VolumeUnderFlowException extends Exception{
    public VolumeUnderFlowException (String message) {
        super(message);
    }
    public VolumeUnderFlowException () {
        super();
    }
}
