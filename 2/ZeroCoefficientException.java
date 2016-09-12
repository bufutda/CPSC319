/**
 * Exception for when coefficients are 0 when they shouldn't be
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Feb 2016
 */
public class ZeroCoefficientException extends PLLException {
    private static final long serialVersionUID = 6448362353905305419L;

    public ZeroCoefficientException () {
        super("the coefficient was 0");
    }
}
