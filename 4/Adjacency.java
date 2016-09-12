/**
 * Node class for an AdjacencyLinkedList
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 */
public class Adjacency {
    /**
     * The destination vertex of the adjacency (the source is the AdjacencyLinkedList's parent vertex index)
     */
    private int node;

    /**
     * The weight of the adjacency
     */
    private int weight;

    /**
     * The next node in the list
     */
    private Adjacency next;

    /**
     * Constructs an adjacency object
     * @constructor
     * @param node - the destination vertex
     * @param weight - the weight of the edge
     */
    public Adjacency (int node, int weight) {
        this.node = node;
        this.weight = weight;
        next = null;
    }

    /**
     * Getter function for the destination vertex index
     * @returns the destination vertex index
     */
    public int getNode () {
        return node;
    }

    /**
     * Getter function for the weight of the edge
     * @returns the weight of the edge
     */
    public int getWeight () {
        return weight;
    }

    /**
     * Getter function for the next node in the list
     * @returns the next node, or null of there isn't one
     */
    public Adjacency getNext () {
        return next;
    }

    /**
     * Setter function for the next node in the list
     * @param next - the next node
     */
    public void setNext (Adjacency next) {
        this.next = next;
    }
}
