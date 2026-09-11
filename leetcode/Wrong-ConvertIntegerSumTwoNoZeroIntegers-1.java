// https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/
// TODO -- need to analyze why it fails for 3027!
// TODO -- we can try BRUTE FORCE!

class Solution {
    public int[] getNoZeroIntegers(int n) {
        int p10 = 1;
        int a = 0, b = 0;
        int carry = 0;
        while(n > 0) {
            
            int digit = n % 10;
            n = n / 10;

            if(digit < 3 && n > 0) {
                // we will try to avoid 0 digits in either a or b
                // we need to add one more condition if it's not a last digit 
                digit += 10;
            }
            // if we have carry we need to consider that
            digit -= carry;

            if(digit == 0) {
                // if last digit is 1 and it's considered previous carry
                break;
            }

            if(digit >= 10) {
                // even after considering carry if it's till > 10
                // we need to carry it
                carry = 1;
            } else {
                carry = 0; // reset carry
            }

            if(digit >= 10) {
                // not set digits for a and b
                a = a + 5 * p10; // 5 for a
                b = b + (digit  - 5) * p10; // rest we will consider for b
            } else {
                a = a + 1 * p10; // we will consider 1 for a
                b = b + (digit  - 1) * p10; // rest we will consider for b
            }

            p10 = p10 * 10;
        }

        return new int[]{a, b};
    }
}