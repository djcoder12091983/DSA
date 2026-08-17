// https://leetcode.com/problems/ugly-number/

class Solution {

    // TODO can we think to solve optimally, implementation code seems to be bit messy

    // let's go with brute force approach
    // TODO then we can think of "Sieve of Eratosthenes" to find prime factors
    
    boolean isPrime(int x) {
        if(x == 1) {
            return false;
        }

        if(x <= 3) {
            return true;
        }

        int l = Double.valueOf(Math.ceil(Math.sqrt(x))).intValue();

        for(int i = 2; i <= l; i++) {
            if(x % i == 0) {
                return false; // not a prime
            }
        }

        return true;
    }

    public boolean isUgly(int n) {

        if(n <= 0) {
            // edge case, ugly number is always positive number
            return false;
        }

        if(n <= 3) {
            return true; // edge case
        }

        // square root algorith to find all factors
        int l = Double.valueOf(Math.ceil(Math.sqrt(n))).intValue();

        for(int i = 1; i <= l; i++) {
            if(n % i == 0) {
                int f = i;
                if(isPrime(f) && f != 2 && f != 3 && f != 5) {
                    return false; // does not share 2, 3, 5 factors
                }

                f = n / i; // other factor
                if(isPrime(f) && f != 2 && f != 3 && f != 5) {
                    return false; // does not share 2, 3, 5 factors
                }
            }
        }

        return true; // all 2, 3, 5 factors found
    }
}