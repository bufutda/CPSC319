/**
 * A linked-list container.
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 */
public class AdjacencyLinkedList {
    /**
     * The head node of the list
     */
    private Adjacency head;

    /**
     * The vertex in the AdjacencyList array that this list corresponds to
     */
    private int parent;

    /**
     * Distance from the parent vertex to the destination vertex
     */
    private int dist = Integer.MAX_VALUE;

    /**
     * The previous vertex in the path to the destination
     */
    private int back;

    /**
     * Constructs a new empty AdjacencyLinkedList object
     * @constructor
     * @param n - the index of the AdjacencyList that this list belongs to
     */
    public AdjacencyLinkedList (int n) {
        this.head = null;
        parent = n;
    }

    /**
     * Append Adjacency onto the end of the list
     * @throws DuplicateEdgeException when the adjacency already exists
     * @param node - the node to add
     * @param weight - the weight of the new adjacency
     */
    public void setNode (int node, int weight) throws DuplicateEdgeException {
        if (head == null) {
            head = new Adjacency(node, weight);
        } else {
            Adjacency n = head;
            while (n != null) {
                if (n.getNode() == node) {
                    throw new DuplicateEdgeException();
                }
                if (n.getNext() == null) {
                    n.setNext(new Adjacency(node, weight));
                    return;
                }
                n = n.getNext();
            }
        }
    }

    /**
     * Getter function for the head adjacency
     * @returns the head adjacency
     */
    public Adjacency getHead () {
        return head;
    }

    /**
     * Getter function for the parent vertex index
     * @returns the parent vertex index
     */
    public int getParent () {
        return parent;
    }

    /**
     * Getter function for the distance to the destination
     * @returns the distance to the destination
     */
    public int getDist () {
        return dist;
    }

    /**
     * Setter function for the distance to the destination
     * @param d - the new distance
     */
    public void setDist (int d) {
        dist = d;
    }

    /**
     * Getter function for the return-path vertex index
     * @returns the return-path vertex index
     */
    public int getBack () {
        return back;
    }

    /**
     * Setter function for the return-path vertex index
     * @param n - the return-path vertex index
     */
    public void setBack (int n) {
        back = n;
    }
}
