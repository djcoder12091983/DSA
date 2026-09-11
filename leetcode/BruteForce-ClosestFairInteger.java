// https://leetcode.com/problems/closest-fair-integer/
// TODO - solved using some SMART Brutee Force, can we think of better approach with simpler implementation
// chosen Brute Force because of some digit wise checking and putting suitable digits has got some lot of
// edge cases and all, if we could generalize or have some smpler then we can think of that as well!

class Solution {

    int digits(int n) {
        int c = 0;
        while(n > 0) {
            n = n / 10;
            c++;
        }

        return c;
    }

    boolean valid(int n) {
        int e = 0, o = 0;
        while(n > 0) {
            int rem = n % 10;
            if(rem % 2 == 0) {
                e++;
            } else {
                o++;
            }

            n = n / 10;
        }

        return e == o;
    }

    static final int MAX = Integer.MAX_VALUE;

    public int closestFair(int n) {
        // the idea is brute force find from a smallest start point
        // if the start point is close then we will be able to find it so fast
        // hence number of steps would be quite smaller
        int c = digits(n);
        if(c % 2 == 1) {
            // odd we will start from next smallest even number of digits 10 power digits
            int start = Double.valueOf(Math.pow(10, c)).intValue();
            while(start < MAX) {
                if(valid(start)) {
                    return start;
                }

                start++;
            }
        } else {
            // we will start from number itself but if not able to find till 10 power (digits)
            // then we will start from  10 power (digits + 1)
            int start = n;
            int end = Double.valueOf(Math.pow(10, c)).intValue();
            while(start < end) {
                if(valid(start)) {
                    return start;
                }

                start++;
            }

            // next search
            start = Double.valueOf(Math.pow(10, c + 1)).intValue();
            while(start < MAX) {
                if(valid(start)) {
                    return start;
                }

                start++;
            }
        }

        return - 1; // dummy value, it won't happen
    }
}