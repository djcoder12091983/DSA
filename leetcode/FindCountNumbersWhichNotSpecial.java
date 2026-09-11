// https://leetcode.com/problems/find-the-count-of-numbers-which-are-not-special/
// TODO can we reduce execution time

class Solution {

    public int nonSpecialCount(int l, int r) {
        // only having eact 2 proper divisor is prime sqaured numbers

        int limit = 100000; // input is 100000000 so we can manage it
        // SOE -- Sieve of Eratosthenes
        boolean[] isPrime = new boolean[limit + 1]; // exclude n
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        // Loop up to the square root of n
        for (int p = 2; p * p <= limit; p++) {
            // If isPrime[p] is not changed, then it is a prime
            if (isPrime[p]) {
                // Update all multiples of p greater than or equal to the square of it
                // Numbers less than p*p have already been marked by smaller primes
                for (int i = p * p; i <= limit; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        int p = 0;
        for(int i = 2; i <= limit; i++) {
            if(isPrime[i] == true) {
                primes.add(i);
            }
        }

        int i = 0;
        // this code style seems to be ODD
        // TODO can we think better
        long start = l, end = r;
        while(1L * primes.get(i) * primes.get(i) < start) {
            i++;
        }
        int c = 0;
        while(1L* primes.get(i) * primes.get(i) <= end) {
            c++;
            i++;
        }

        return r - l + 1 - c;
    }
}