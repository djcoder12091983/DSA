// https://leetcode.com/problems/divide-two-integers/

class Solution {
    public int divide(int dividend, int divisor) {
        // we will use bit manipulation
        // we will see if turn on a bit for a quotient then check
        // whether result (divisor * quotient) still < dividend

        long A = dividend, B = divisor;

        // preseve sign before change them to + value
        boolean sign1 = A < 0, sign2 = B < 0;
        boolean sign3 = sign1 ^ sign2;

        // we will work absolute value to make ocmputation easy
        A = Math.abs(A);
        B = Math.abs(B);
        
        // precompute (divisor * 2 power bit index) to avoid multiplication
        // we will use addition
        long divisor2Power[] = new long[32];
        divisor2Power[0] = B;
        for(int i = 1; i < 32; i++) {
            divisor2Power[i] = divisor2Power[i - 1] + divisor2Power[i - 1]; // multiplied by 2
        }

        long ans = 0;
        // bit wise check
        for(int i = 31; i >= 0; i--) {
            long t = A - divisor2Power[i];
            if(t >= 0) {
                // valid move -- turn on the bit in result
                ans += 1L << i;
                A = t; // change A
            }
        }

        // System.out.println(ans);

        if(sign3 == true) {
            // result will be in negative value
            ans = ~ans + 1; // assume 2's complement is used to repreent negative number
        }

        // avoid overflow issue
        if(ans > Integer.MAX_VALUE) {
            ans = Integer.MAX_VALUE;
        }

        if(ans < Integer.MIN_VALUE) {
            ans = Integer.MIN_VALUE;
        }

        return Long.valueOf(ans).intValue();
    }
}