/**
 * Implements a simple key-search through a binary search tree.
 * Designed for a homework assignment in CPSC 319 at the University of Calgary.
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since January 23, 2016
 */
public class HW1a {
    /**
     * Tests whether or not a string is a valid representation of an Integer.
     * @param s the String to Test
     * @return Boolean true if the s can be parsed to an Integer, false if otherwise
     */
    public static boolean isInt(String s) {
        try {
            int n = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Traverses recursivley through a binary search tree looking for a key.
     * @param src an Integer BST array to search
     * @param key the Integer to search for
     * @param low the index to start looking
     * @param high the index to stop looking
     * @return the Integer index of the key, or -1 if it is not found
     */
    public static int binSearch(int[] src, int key, int low, int high) {
        int middle = (high + low) / 2;

        if (low > high) // key not found
            return -1;
        else if (key < src[middle])
            return binSearch(src, key, low, middle - 1); // partition and search the bottom of the array
        else if (key > src[middle])
            return binSearch(src, key, middle + 1, high); // partition and search the top of the array
        else
            return middle; // success
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java HW1a N key");
            System.exit(1);
        } else {
            if (isInt(args[0]) && isInt(args[1]) && Integer.parseInt(args[0]) >= 0) { // input validation
                int key = Integer.parseInt(args[1]);
                int[] arr = new int[Integer.parseInt(args[0])]; // N element array

                for (int i = 0; i < arr.length; i++)
                    arr[i] = i; // set array element to its index

                System.out.printf("Begginning search for integer: %d in an array of size %d.\n", key, arr.length);
                long start = System.nanoTime();
                int index = binSearch(arr, key, 0, arr.length - 1);
                long end = System.nanoTime();

                if (index == -1) // worst case
                    System.out.printf("It took %dns to search through the array of size %d. The integer %d was not found. (Worst Case)\n", end - start, arr.length, key);
                else if (index == 0) // best case
                    System.out.printf("It took %dns to search through the array of size %d. %d was found at index %d. (Best Case)\n", end - start, arr.length, key, index);
                else
                    System.out.printf("It took %dns to search through the array of size %d. %d was found at index %d.", end - start, arr.length, key, index);
            } else {
                System.err.println("Usage: java HW1a N key");
                System.err.println("Both N and key must be integers. N must be positive.");
                System.exit(1);
            }
        }
    }
}
