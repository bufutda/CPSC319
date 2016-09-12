/**
 * Primes utility for computing table sizes
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since April, 2016
 */
public class Prime {
    /**
     * Detects whether or not n is prime
     * @param n - the number to test
     * @retuns true if n is prime, false otherwise
     */
    public static boolean isPrime (int n) {
        if (n % 2 == 0) {
            return false;
        }
        for(int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Computes the smallest prime >= n
     * @param n - the lower bound on the prime
     * @returns the smallest prime after n
     */
    public static int nextPrime (int n) {
        while (true) {
            if (isPrime(n)) {
                return n;
            }
            n++;
        }
    }
}
