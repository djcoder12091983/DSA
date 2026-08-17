// https://leetcode.com/problems/ugly-number/
// TODO need to track if factor is prime or not

class Solution {
    public boolean isUgly(int n) {

        if(n == 1) {
            return true; // edge case
        }

        // square root algorith to find prime factors
        int l = Double.valueOf(Math.ceil(Math.sqrt(n))).intValue();

        for(int i = 2; i <= l; i++) {
            if(n % i == 0) {
                int f = i;
                if(f != 2 && f != 3 && f != 5) {
                    return false; // does not share 2, 3, 5 factors
                }

                f = n / i; // other factor
                if(f != 2 && f != 3 && f != 5) {
                    return false; // does not share 2, 3, 5 factors
                }
            }
        }

        return true; // all 2, 3, 5 factors found
    }
}