/**
 * Exception for when exponents are negative
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Feb 2016
 */
public class NegativeExponentException extends PLLException {
    private static final long serialVersionUID = -2335779731442437107L;

    public NegativeExponentException (int e) {
        super(e + " is below 0");
    }
}
