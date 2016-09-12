/**
 * A linked-list container that is capable of representing a univariate polynomal.
 * @author Mitchell Sawatzky
 * @since Feb 2016
 * @version 1.0
 */
public class PLL {
    private PLLNode head;

    /**
     * Constructs a new empty PolynomialLinkedList object
     * @constructor
     */
    public PLL () {
        this.head = null;
    }

    /**
     * Retrieve the node of the term with a given exponent
     * @throws NegativeExponentException if e is below 0
     * @param e - the exponent identifying the term
     * @return the term node
     */
    public PLLNode getNode (int e) throws NegativeExponentException {
        if (e < 0) {
            throw new NegativeExponentException(e);
        }
        if (head == null) {
            return null;
        }
        PLLNode n = head;
        while (n != null) {
            if (n.getExp() == e) {
                return n;
            }
            n = n.getNextNode();
        }
        return null;
    }

    /**
     * Eject a node from the list if it exists
     * @throws NodeNotFoundException when node is not in the list
     * @param node - the node before the node to eject
     */
    public void ejectNode (PLLNode node) throws NodeNotFoundException {
        if (node == head) {
            head = head.getNextNode();
        } else {
            PLLNode n = head;
            while (n.getNextNode() != node) {
                if (n.getNextNode() == null) {
                    throw new NodeNotFoundException(node);
                }
                n = n.getNextNode();
            }
            n.setNextNode(node.getNextNode());
        }
    }

    /**
     * If a node exists in the list with exp e, update it's coefficient, otherwise splice in a new node
     * @param c - the updated coefficient
     * @param e - the exponent identifying the term
     */
    public void setNode (int c, int e) throws ZeroCoefficientException, NegativeExponentException {
        if (e < 0) {
            throw new NegativeExponentException(e);
        }
        if (head == null) {
            head = new PLLNode(c, e, null);
        } else if (e < head.getExp()) {
            head = new PLLNode(c, e, head);
        } else {
            PLLNode n = head;
            while (n != null) {
                if (n.getExp() == e) {
                    n.setCoeff(c);
                    return;
                }
                if (n.getNextNode() == null) {
                    n.setNextNode(new PLLNode(c, e, null));
                    return;
                }
                if (n.getExp() < e && n.getNextNode().getExp() > e) {
                    n.setNextNode(new PLLNode(c, e, n.getNextNode()));
                    return;
                }
                n = n.getNextNode();
            }
        }
    }

    /**
     * Getter function for the head node
     * @returns the head node
     */
    public PLLNode getHead() {
        return head;
    }
}
