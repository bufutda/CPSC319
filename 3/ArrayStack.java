/**
 * Stack implementation using an array for use with pre/postfix shunting algorithms
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Mar 2016
 */
public class ArrayStack {
    /**
     * The stack array
     */
    String[] stack;

    /**
     * The index of the top element in the stack
     */
    int top;

    /**
     * Constructs an ArrayStack object with a capacity of depth
     * @constructor
     * @param depth - the capacity of the stack
     */
    public ArrayStack (int depth) {
        stack = new String[depth];
        top = 0;
    }

    /**
     * Push an element onto the stack
     * @param elem - the element to push on the stack
     */
    public void push (String elem) {
        stack[++top] = elem;
    }

    /**
     * Pop an element off the stack
     * @returns the top element
     */
    public String pop () {
        return stack[top--];
    }

    /**
     * Look at the top element without popping it
     * @retuns the top element
     */
    public String peek () {
        return stack[top];
    }

    /**
     * Check if the stack is empty
     * @returns true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return top == 0;
    }
}
