// https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
// Wrong approach -- some cases will fail like -- 514 gives 111,403 but but we need to avoid 0 other than last digit

class Solution {
    public int[] getNoZeroIntegers(int n) {
        int p10 = 1;
        int a = 0, b = 0;
        int carry = 0;
        while(n > 0) {
            int digit = n % 10;

            int t = carry;
            if(digit == 0) {
                // we will consider it as a 10
                digit = 10;
                carry = 1;
            } else {
                carry = 0; // reset carry
            }

            // if there is a carry we need consider that
            digit = digit - t;
            a = a + 1 * p10; // we will consider 1 for a
            b = b + (digit  - 1) * p10; // rest we will consider for b

            n = n / 10;
            p10 = p10 * 10;
        }

        return new int[]{a, b};
    }
}