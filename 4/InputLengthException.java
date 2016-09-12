/**
 * Exception for use when an input is the wrong length
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 * @extends Exception
 */
public class InputLengthException extends Exception {
    static final long serialVersionUID = 1L;

    /**
     * Construct an InputLengthException object with message m
     * @constructor
     * @param m - the message
     */
    public InputLengthException (String m) {
        super(m);
    }
}
