/**
 * Exception for use when an unexpected token is encountered
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 * @extends Exception
 */
public class UnexpectedTokenException extends Exception {
    static final long serialVersionUID = 1L;

    /**
     * Constructs an UnexpectedTokenException object with message m
     * @constructor
     * @param m - the message
     */
    public UnexpectedTokenException (String m) {
        super(m);
    }
}
