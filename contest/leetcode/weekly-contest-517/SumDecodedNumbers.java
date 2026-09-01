// https://leetcode.com/problems/sum-of-decoded-numbers/

class Solution {

    int digits(long X) {
        int c = 0;
        while(X > 0) {
            X = X / 10;
            c++;
        }

        return c;
    }

    static final long MOD = 1000000000 + 7;

    long pow(long X , long Y) {
        long p = 1;
        X = X % MOD;

        while (Y > 0) {
            // If exponent is odd, multiply base with the result
            if ((Y & 1) == 1) {
                p = (p * X) % MOD;
            }

            // half it
            Y = Y >> 1;
            // Square the base
            X = (X * X) % MOD;
        }

        return p;
    }

    long convert(long X) {
        int w = Long.valueOf(X % 10).intValue();
        long d = X / 10;

        long t = Double.valueOf(Math.pow(10, digits(d) - w)).longValue();
        long x = d / t;
        long y = d % t;

        // System.out.println(w + " " + d + " " + x + " " + y);

        long p = pow(x, y);
        return p;
    }
    
    public int sumDecoded(long[] A) {
        long sum = 0;
        int N = A.length;
        for(int i = 0; i < N; i++) {
            sum = (sum + convert(A[i])) % MOD;
        }

        return Long.valueOf(sum).intValue();
    }
}