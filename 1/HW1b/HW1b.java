/**
 * Implements a bubble-down version of bubble sort.
 * Designed for a homework assignment in CPSC 319 at the University of Calgary.
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since January 23, 2016
 */
public class HW1b {
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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java HW1b N");
            System.exit(1);
        } else {
            if (isInt(args[0]) && Integer.parseInt(args[0]) >= 0) { // input validation
                int[] arr = new int[Integer.parseInt(args[0])]; // N element array

                System.out.println("Before sorting: [");
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = (int)(Math.random() * 1000000); // create random number
                    System.out.print("  " + arr[i] + (i == arr.length - 1 ? "\n" : ",\n")); // print element
                }
                System.out.println("]");

                // sort using bubble-down sort
                for (int i = arr.length - 1; i > 0; i--) {
                    for (int j = 0; j < i; j++) {
                        if (arr[j] > arr[j + 1]) { // if the next element is smaller, swap
                            int temp = arr[j + 1];
                            arr[j + 1] = arr[j];
                            arr[j] = temp;
                        }
                    }
                }

                System.out.println("After sorting: [");
                for (int i = 0; i < arr.length; i++)
                    System.out.print("  " + arr[i] + (i == arr.length - 1 ? "\n" : ",\n")); // print element
                System.out.println("]");
            } else {
                System.err.println("Usage: java HW1b N");
                System.err.println("N must be a positive integer.");
                System.exit(1);
            }
        }
    }
}
