/**
 * Implements a benchmark for simple search traversal through a binary search tree.
 * Designed for a homework assignment in CPSC 319 at the University of Calgary.
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since January 23, 2016
 */
public class BinarySearchBM {
    /**
     * Iterative Binary Search algorithm taken from CPSC 319 - Searching and Sorting (Slide 6/24)
     * @param arr a sorted Integer array to use as the BST
     * @param key the Integer to search for in the BST
     * @return index of key, or -1 if not found
     */
    public static int binSearch (int[] arr, int key) {
        int lo = 0, mid, hi = arr.length - 1;
        while (lo <= hi) { // while there are still partitions to search
            mid = (lo + hi) / 2;
            if (key < arr[mid])
                hi = mid - 1;
            else if (arr[mid] < key)
                lo = mid + 1;
            else // key == arr[mid]
                return mid;
        }
        return -1; // key not found
    }

    public static void main(String[] args) {
        int pow = 1;
        int trials = 50;
        for (int i = 1; i <= 6; i++) {
            pow *= 10;
            int [] arr = new int[pow]; // 10^i element array
            System.out.println("Binary Search Benchmark for array with length " + arr.length + ":");

            long runningAverage = 0;
            for (int j = 0; j < trials; j++) {
                long start = System.nanoTime();
                binSearch(arr, -1);
                long end = System.nanoTime();
                runningAverage += (end - start);
            }
            System.out.println("\t  Worse Case (Average over " + trials + " trials): " + (runningAverage / trials) + "ns");

            runningAverage = 0;
            for (int k = 0; k < trials; k++) {
                long trialAvg = 0;
                for (int j = 0; j < arr.length + 5; j++) {
                    long start = System.nanoTime();
                    binSearch(arr, j);
                    long end = System.nanoTime();
                    trialAvg += (end - start);
                }
                runningAverage += (trialAvg / (arr.length + 5));
            }
            System.out.println("\tAverage Case (Average over " + trials + " trials): " + (runningAverage / trials) + "ns");
        }
    }
}
