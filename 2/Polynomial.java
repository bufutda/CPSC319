/**
 * An implementation of a univariate polynomial using a PolynomialLinkedList
 * @author Mitchell Sawatzky
 * @since Feb 2016
 * @version 1.0
 */
public class Polynomial {
    private PLL poly;

    /**
     * Constructs an empty Polynomial object
     * @constructor
     */
    public Polynomial () {
        this.poly = new PLL();
    }

    /**
     * Constructs a Polynomial object according to the input string
     * @constructor
     * @throws PLLException when the st is invalid
     * @param st - the input space-delimited polynomial string
     */
    public Polynomial (String st) throws PLLException {
        try {
            this.poly = new PLL();
            String[] arr = st.split(" ");
            for (int i = 0; i < arr.length; i += 2) {
                int coeff = Integer.parseInt(arr[i]);
                int exp = Integer.parseInt(arr[i + 1]);
                if (coeff != 0) {
                    PLLNode already = poly.getNode(exp);
                    if (already == null) {
                        poly.setNode(coeff, exp);
                    } else {
                        int multi = coeff + already.getCoeff();
                        if (multi == 0) {
                            poly.ejectNode(already);
                        } else {
                            poly.setNode(multi, exp);
                        }
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidPolynomialStringException(st);
        }
    }

    /**
     * Getter function for poly
     * @return the PLL poly
     */
    public PLL getPoly () {
        return poly;
    }

    /**
     * Preforms arithmetic on two polynomials and returns the result in a new Polynomial
     * @param b the second polynomial
     * @param addSub true if the operation is +, false if it's negative
     * @returns the resultant Polynomial
     */
    private Polynomial arithmetic (Polynomial b, boolean addSub) {
        Polynomial res = new Polynomial();
        PLL rP = res.getPoly();
        PLLNode aN = poly.getHead();
        PLLNode bN = b.getPoly().getHead();
        try {
            while (aN != null || bN != null) {
                if (aN != null && bN != null) {
                    int aE = aN.getExp();
                    int bE = bN.getExp();
                    int aC = aN.getCoeff();
                    int bC = addSub ? bN.getCoeff() : -1 * bN.getCoeff();
                    if (aE == bE) {
                        int multi = aC + bC;
                        if (multi != 0) {
                            rP.setNode(multi, aE);
                        }
                        aN = aN.getNextNode();
                        bN = bN.getNextNode();
                    } else if (aE < bE) {
                        rP.setNode(aC, aE);
                        aN = aN.getNextNode();
                    } else {
                        rP.setNode(bC, bE);
                        bN = bN.getNextNode();
                    }
                } else if (aN == null) {
                    while (bN != null) {
                        rP.setNode(bN.getCoeff(), bN.getExp());
                        bN = bN.getNextNode();
                    }
                } else {
                    while (aN != null) {
                        rP.setNode(aN.getCoeff(), aN.getExp());
                        aN = aN.getNextNode();
                    }
                }
            }
        } catch (PLLException e) {
            System.err.println("Something is horribly wrong");
            System.err.println(e);
            System.exit(1);
        }
        return res;
    }

    /**
     * Adds a Polynomial b to this
     * @param b - the polynomial to add to
     * @returns the polynomial result
     */
    public Polynomial add (Polynomial b) {
        return arithmetic(b, true);
    }

    /**
     * Subtracts a Polynomial b from this
     * @param b - the polynomial to subtract from this
     * @returns the polynomial result
     */
    public Polynomial subtract(Polynomial b) {
        return arithmetic(b, false);
    }

    /**
     * Converts the polynomial to a valid polynomial string and returns it
     * @returns the string form of a polynomial
     */
    public String toString () {
        PLLNode n = poly.getHead();
        if (n == null) {
            return "0";
        } else {
            String res = Integer.toString(n.getCoeff()) + " " + Integer.toString(n.getExp());
            n = n.getNextNode();
            while (n != null) {
                res += " " + Integer.toString(n.getCoeff());
                res += " " + Integer.toString(n.getExp());
                n = n.getNextNode();
            }
            return res;
        }
    }

    /**
     * Computes the polynomial at a specific value x
     * @param x - the value to compute the polynomial at
     * @returns the integer result
     */
    public int evaluate (int x) {
        PLLNode n = poly.getHead();
        int res = 0;
        while (n != null) {
            int pow = 1;
            for (int i = 0; i < n.getExp(); i++) {
                pow *= x;
            }
            res += n.getCoeff() * pow;
            n = n.getNextNode();
        }
        return res;
    }
}
