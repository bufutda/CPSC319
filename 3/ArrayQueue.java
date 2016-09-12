/**
 * A queue implementation using Arrays for use with pre/postfix shunting algorithms
 * @author Mitchell Sawatzky
 * @since Mar 2016
 * @version 1.0
 */
public class ArrayQueue {
    /**
     * The queue array
     */
    private String[] q;

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
    public ArrayQueue (int length) {
        size = length;
        q = new String[size];
        front = -1;
        back = -1;
    }

    /**
     * Add an element onto the back of the queue
     * @param elem - the element to add to the queue
     */
    public void enqueue (String elem) {
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
    public String dequeue () {
        String elem = q[front];
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
}
