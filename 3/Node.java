/**
 * A data node for the BinaryExpressionTree class
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class Node {
    /**
     * The left node
     */
    private Node left;
    /**
     * The right node
     */
    private Node right;
    /**
     * The value of the node
     */
    private String data;

    /**
     * Constructs a new node with no children
     * @constructor
     * @param data - the value of the node
     */
    public Node (String data) {
        this(data, null, null);
    }

    /**
     * Constructs a new node with the given data and children
     * @constructor
     * @param data - the value of the node
     * @param left - the left child node
     * @param right - the right child node
     */
    public Node (String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Getter function for the left node
     * @returns the left child node
     */
    public Node getLeftNode () {
        return left;
    }

    /**
     * Getter function for the right child node
     * @returns the right child node
     */
    public Node getRightNode () {
        return right;
    }

    /**
     * Getter frunction for the node data
     * @returns the node data
     */
    public String getData () {
        return data;
    }
}
