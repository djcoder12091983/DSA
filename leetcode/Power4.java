// https://leetcode.com/problems/power-of-four/submissions/2103621813/

import java.math.*;

class Solution {

    // use long to avoid overflow
    // apply BS to find whether it's square or not
    long sqaure(long n) {
        long l = 1, r = n - 1;
        while(l <= r) {
            long mid = (l + r) / 2;
            long x = mid * mid;
            if(x == n) {
                return mid;
            } else if(x > n) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return -1; // not possible
    }

    // process -2
    boolean check2(int n) {
        // keep on dividing by 4 only left most remainder will be one remaining it will be 0 only
        int c = 0;
        while(n > 1) {
            int rem = n % 4;
            if(rem > 0) {
                // if any remainder greater than 0 then it's false
                // because this time n > 1 onmly last remainder will be 1 and remaining all will be 0
                return false;
            }

            n = n / 4;
        }

        return n == 1;
    }

    // process - 1
    boolean check1(int n) {
        long x = sqaure(n);

        // we can break into two subproblems like one is 2 power which we can
        // check by checking all 0 bits except left most bit
        // IDEA is like need to square of 2 power, another subproblem is sqaure root check

        if(x == -1) {
            return false; // it's not a square
        }

        //System.out.println("x: " + x);

        // now check whether it's 2 power or not
        int c = 0;
        while(x > 0) {
            if((x & 1) > 0) {
                // set bit
                c++;
            }

            if(c > 1) {
                // not possible
                return false;
            }

            x >>= 1;
        }

        return true; // only one set bit so it's 2 power
    }

    // process - 3
    boolean check3(int n) {
        // use log method
        double x = Math.log(n) / Math.log(4);
        //BigDecimal bd = new BigDecimal(x);
        //int y = bd.intValue();
        //System.out.println("x : " + x + " y: " + y);
        int y = (int)x;
        if(1.0 * y == x) {
            // it's a whole number
            return true;
        } else {
            // fraction soit's not possible
            return false;
        }
    }

    public boolean isPowerOfFour(int n) {
        // divisibility of 4 (WRONG)
        /*
        if((n & 1) == 0) {
            n = n / 2;
            if((n & 1) == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
        */

        if(n == 1) {
            return true; // handle one edge case
        }

        if(n <= 0) {
            // if it's 0 or negative anyways it's possible to be any power of positive value
            return false;
        }

        // return check1(n); // process - 1
        
        // return check2(n); // process - 2

        return check3(n); // process - 3 log method
    }
}