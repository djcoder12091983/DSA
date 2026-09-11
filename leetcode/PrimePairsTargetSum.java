// https://leetcode.com/problems/prime-pairs-with-target-sum/

class Solution {
    public List<List<Integer>> findPrimePairs(int n) {

        if(n == 2) {
            // not possible
            return new ArrayList<>();
        }
        
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

        List<List<Integer>> ans = new ArrayList<>();
        for(int x = 2; x <= n/2 ; x++) {
            int y = n - x;
            if(isPrime[x] && isPrime[y]) {
                // found
                List<Integer> found = new ArrayList<>(2);
                found.add(x);
                found.add(y);
                
                ans.add(found);
            }
        }

        return ans;
    }
}