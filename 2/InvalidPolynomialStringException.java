/**
 * Exception for the input string to a polynomial is invalid
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Feb 2016
 */
public class InvalidPolynomialStringException extends PLLException {
    private static final long serialVersionUID = 2742636283580586942L;

    public InvalidPolynomialStringException (String st) {
        super("too many coefficients or not enough exponents: " + st);
    }
}
