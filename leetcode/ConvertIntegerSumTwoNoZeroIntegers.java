// https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/

class Solution {

    boolean containZeros(int n) {
        while(n > 0) {
            if(n % 10 == 0) {
                return true;
            }

            n = n / 10;
        }

        return false;
    }

    public int[] getNoZeroIntegers(int n) {
        // digit by digit sum it seems to be TRICK in some cases!
        int a = n - 1, b = 1;
        while(a > 0) {
            if(!containZeros(a)) {
                b = n - a;
                if(!containZeros(b)) {
                    break;
                }
            }

            a--;
        }

        return new int[]{a, b};
    }
}