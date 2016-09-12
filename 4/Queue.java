/**
 * A queue implementation using Arrays
 * @author Mitchell Sawatzky
 * @since March 2016
 * @version 1.0
 */
public class Queue {
    /**
     * The queue array
     */
    private AdjacencyLinkedList[] q;

    /**
     * The index of the front element
     */
    private int front;

    /**
     * The index of the back element
     */
    private int back;

    /**
     * The capacity of the queue
     */
    private int size;

    /**
     * Constructs a new queue with a capacity of length
     * @constructor
     * @param length - the capacity of the queue
     */
    public Queue (int length) {
        size = length;
        q = new AdjacencyLinkedList[size];
        front = -1;
        back = -1;
    }

    /**
     * Add an element onto the back of the queue
     * @param elem - the element to add to the queue
     */
    public void enq (AdjacencyLinkedList elem) {
        if (back == size - 1 || back == -1) {
            q[0] = elem;
            back = 0;
            if (front == -1) {
                front = 0;
            }
        } else {
            back++;
            q[back] = elem;
        }
    }

    /**
     * Remove an element from the front of the queue
     * @returns the element
     */
    public AdjacencyLinkedList deq () {
        AdjacencyLinkedList elem = q[front];
        if (front == back) {
            back = -1;
            front = -1;
        } else if (front == q.length - 1) {
            front = 0;
        } else {
            front++;
        }
        return elem;
    }

    /**
     * Check whether or not the queue is empty
     * @returns true if the queue is empty, false otherwise
     */
    public boolean isEmpty () {
        return (front == -1);
    }

    /**
     * Prints a representation of the queue to stdout
     */
    public void print () {
        if (front == -1) {
            System.out.println("[ EMPTY ]");
        } else {
            System.out.print("[");
            int f = front;
            int b = back;
            while (f != -1) {
                AdjacencyLinkedList elem = q[f];
                if (f == b) {
                    b = -1;
                    f = -1;
                } else if (f == q.length - 1) {
                    f = 0;
                } else {
                    f++;
                }
                System.out.print(" " + elem);
            }
            System.out.println(" ]");
        }
    }
}
