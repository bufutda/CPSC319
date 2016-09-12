/**
 * Exception for use when an edge has been added twice
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 * @extends Exception
 */
public class DuplicateEdgeException extends Exception {
    static final long serialVersionUID = 1L;
    /**
     * Constructs a DuplicateEdgeException object with a message m
     * @constructor
     * @param m - the message
     */
    public DuplicateEdgeException (String m) {
        super(m);
    }
    /**
     * Constructs a DuplicateEdgeException object with no message
     * @constructor
     */
    public DuplicateEdgeException () {
        super("");
    }
}
