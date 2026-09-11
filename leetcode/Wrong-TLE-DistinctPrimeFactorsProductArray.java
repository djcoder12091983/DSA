// https://leetcode.com/problems/distinct-prime-factors-of-product-of-array/
// TODO -- as per input constraints solution will get overflow and TLE issue

class Solution {

    boolean isPrime(int x) {

        if(x == 1) {
            return false; // 1 is not prime
        }

        int i = 2;
        while(i * i <= x) {
            if(x % i == 0) {
                return false;
            }

            i++;
        }

        return true;
    }

    public int distinctPrimeFactors(int[] A) {
        // BRUTE force
        
        int N = A.length;
        int p = 1;
        for(int i = 0; i < N; i++) {
            p = p * A[i];
        }

        int k = 1;
        Set<Integer> primes = new HashSet<>(); // distinct primes
        while(k * k <= p) {
            if(p % k == 0) {
                if(isPrime(k)) {
                    primes.add(k);
                }

                if(isPrime(p / k)) {
                    // another factor
                    primes.add(p / k);
                }
            }

            k++;
        }

        System.out.println("P: " + p + " Primes: " + primes);

        return primes.size();
    }
}