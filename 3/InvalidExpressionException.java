/**
 * Exception class for when something goes wrong when parsing an expression
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class InvalidExpressionException extends Exception {
    /**
     * UID for object serialization
     */
    public static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception object with the given error and expression
     * @consructor
     * @param error - the error text
     * @param exp - the expression that caused the exception
     */
    public InvalidExpressionException (String error, String exp) {
        super("Invalid Expression (" + error + "): " + exp);
    }
}
