/**
 * A node in a PolynomialLinkedList
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Feb 2016
 */
public class PLLNode {
    private int c;
    private int e;
    private PLLNode next;

    /**
     * Constructs a new PLLNode object
     * @constructor
     * @throws ZeroCoefficientException if coeff is zero
     * @throws NegativeExponentException if exp is less than zero
     * @param coeff - the coefficient of the term
     * @param exp - the exponent of the term
     * @param nextNode - the next node in the list
     */
    public PLLNode (int coeff, int exp, PLLNode nextNode) throws ZeroCoefficientException, NegativeExponentException {
        setCoeff(coeff);
        setExp(exp);
        setNextNode(nextNode);
    }

    /**
     * Getter function for the next node in the list
     * @return the next PLLNode in the list
     */
    public PLLNode getNextNode () {
        return next;
    }

    /**
     * Setter function for the next node in the list
     * @param nextNode - the new node to set as the next node in the list
     */
    protected void setNextNode (PLLNode nextNode) {
        this.next = nextNode;
    }

    /**
     * Getter function for the coefficient of this node
     * @return the coefficient of the node
     */
    public int getCoeff () {
        return c;
    }

    /**
     * Setter function for the coefficient of this node
     * @throws ZeroCoefficientException if coeff is zero
     * @param coeff - the new coefficient
     */
    public void setCoeff (int coeff) throws ZeroCoefficientException {
        if (coeff == 0) {
            throw new ZeroCoefficientException();
        }
        this.c = coeff;
    }

    /**
     * Getter function for the exponent of this node
     * @return the exponent of this node
     */
    public int getExp () {
        return e;
    }

    /**
     * Setter function for the exponent of this node
     * @throws NegativeExponentException if exp is less than zero
     * @param exp - the new exponent
     */
     private void setExp (int exp) throws NegativeExponentException {
         if (exp < 0) {
             throw new NegativeExponentException(exp);
         }
         this.e = exp;
     }
}
