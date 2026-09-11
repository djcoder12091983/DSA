// https://leetcode.com/problems/count-primes/

class Solution {
    public int countPrimes(int n) {
        // SOE -- Sieve of Eratosthenes
        boolean[] isPrime = new boolean[n]; // exclude n
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        // Loop up to the square root of n
        for (int p = 2; p * p < n; p++) {
            // If isPrime[p] is not changed, then it is a prime
            if (isPrime[p]) {
                // Update all multiples of p greater than or equal to the square of it
                // Numbers less than p*p have already been marked by smaller primes
                for (int i = p * p; i < n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        int p = 0;
        for(int i = 2; i < n; i++) {
            if(isPrime[i] == true) {
                p++;
            }
        }

        return p;
    }
}