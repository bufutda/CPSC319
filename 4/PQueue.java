/**
 * Inefficient priority queue using arrays
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 */
public class PQueue {
    /**
     * The max size of the queue
     */
    private int size;

    /**
     * The array of the queue
     */
    private AdjacencyLinkedList[] pq;

    /**
     * The element count of the queue
     */
    private int elem;

    /**
     * Constructs a PQueue object with max size s
     * @constructor
     * @param s - the maximum size of the queue
     */
    public PQueue (int s) {
        size = s;
        pq = new AdjacencyLinkedList[s];
        elem = 0;
    }

    /**
     * Retrieve and remove the element with the lowest distance from the queue
     * @throws RuntimeException when the queue is empty
     * @returns the element with the highest priority
     */
    public AdjacencyLinkedList pop () {
        int pri = Integer.MAX_VALUE;
        int m = -1;
        for (int i = 0; i < size; i++) {
            if (pq[i] != null && pq[i].getDist() < pri) {
                m = i;
                pri = pq[i].getDist();
            }
        }
        if (m == -1) {
            throw new RuntimeException("Queue is empty");
        } else {
            AdjacencyLinkedList res = pq[m];
            pq[m] = null;
            elem--;
            return res;
        }
    }

    /**
     * Retrieve the element with the lowest distance, but don't alter the queue
     * @throws RuntimeException when the queue is empty
     * @returns the element with the lowest distance
     */
    public AdjacencyLinkedList peek () {
        int pri = Integer.MAX_VALUE;
        int m = -1;
        for (int i = 0; i < size; i++) {
            if (pq[i].getDist() < pri) {
                m = i;
                pri = pq[i].getDist();
            }
        }
        if (m == -1) {
            throw new RuntimeException("Queue is empty");
        } else {
            return pq[m];
        }
    }

    /**
     * Append an element onto the queue
     * @throws RuntimeException when the queue is full
     * @param e - the element to append
     */
    public void push (AdjacencyLinkedList e) {
        for (int i = 0; i < size; i++) {
            if (pq[i] == null) {
                pq[i] = e;
                elem++;
                return;
            }
        }
        throw new RuntimeException("Queue is full");
    }

    /**
     * Remove an element from the queue if it exists
     * @param e - the elemnt to remove
     */
    public void del (AdjacencyLinkedList e) {
        for (int i = 0; i < size; i++) {
            if (pq[i] == e) {
                pq[i] = null;
                elem--;
                return;
            }
        }
    }

    /**
     * Output a representation of the p-queue to stdout
     */
    public void print () {
        for (int i = 0; i < size; i++) {
            if (pq[i] != null) {
                System.out.print("[" + pq[i].getParent() + "," + pq[i].getDist() + "] ");
            }
        }
        System.out.println();
    }

    /**
     * Check whether the queue is empty
     * @returns true if the queue is empty, false otherwise
     */
    public boolean isEmpty () {
        return (elem == 0);
    }

    /**
     * Check whether the queue is full
     * @returns true if the queue is full, false otherwise
     */
    public boolean isFull () {
        return elem == size;
    }
}
