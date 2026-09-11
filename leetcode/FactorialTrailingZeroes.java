// https://leetcode.com/problems/factorial-trailing-zeroes/
// TODO -- we can only look for 5 factors, coz always number of 5 factors will be lesser than 2 factors
// TODO -- we will think we can solve using log(N) time

class Solution {

    int factors(int n, int f) {
        int c = 0;
        while(n > 0) {
            if(n % f == 0) {
                c++;
            } else {
                // once remainder stops getting 0 we are done
                break;
            }
            n /= f;
        }

        return c;
    }

    public int trailingZeroes(int n) {
        int c2 = 0, c5 = 0;
        for(int i = 1; i <= n; i++) {
            // we will count factors for 5 and 2 beause these two will result 10 so zero digit
            c2 += factors(i, 2);
            c5 += factors(i, 5);
        }

        System.out.println(c5 + " " + c2);

        return Math.min(c2, c5);
    }
}