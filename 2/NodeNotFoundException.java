/**
 * Exception for when a node value is requested when the node does not exist
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Feb 2016
 */
public class NodeNotFoundException extends PLLException {
    private static final long serialVersionUID = -2869626677356783585L;
    public NodeNotFoundException(PLLNode node) {
        super("cannot eject a node not in the list (exp " + node.getExp() + ")");
    }
}
