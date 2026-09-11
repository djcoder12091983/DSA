// https://leetcode.com/problems/distinct-prime-factors-of-product-of-array/

class Solution {

    static final int MAX = 1000;
    int[] spf = new int[MAX + 1];

    void createSieve() {
        // Initialize every number's SPF as itself
        for (int i = 1; i <= MAX; i++) {
            spf[i] = i;
        }

        // Explicitly map even numbers to have an SPF of 2
        for (int i = 2; i <= MAX; i += 2) {
            spf[i] = 2;
        }

        // Apply Sieve logic for odd numbers
        for (int i = 3; i * i <= MAX; i += 2) {
            // If i is prime (its SPF is still itself)
            if (spf[i] == i) {
                // Mark SPF for all multiples of i
                for (int j = i * i; j <= MAX; j += i) {
                    // Update only if it hasn't been updated by a smaller prime
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
    }

    void addPrimeFactors(int n, Set<Integer> primes) {
        while (n > 1) {
            primes.add(spf[n]);
            n /= spf[n]; // Divide the number by its smallest prime factor
        }
    }

    public int distinctPrimeFactors(int[] A) {
        createSieve();

        Set<Integer> primes = new HashSet<>();
        for(int x : A) {
            addPrimeFactors(x, primes);
        }

        return primes.size();
    }
}