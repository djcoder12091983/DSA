// https://leetcode.com/problems/minimum-flips-to-make-a-or-b-equal-to-c/

class Solution {
    public int minFlips(int a, int b, int c) {
        // check for 30th bit
        int ans = 0;
        for(int i = 0; i < 30; i++) {
            // check 'a' and 'b' bit status
            int bit = c & (1 << i);
            int bit1 = 0, bit0 = 0;
            if((a & (1 << i)) > 0) {
                bit1++;
            } else {
                bit0++;
            }

            // do the same for b
            if((b & (1 << i)) > 0) {
                bit1++;
            } else {
                bit0++;
            }

            if(bit1 > 0 && bit == 0) {
                // some set bits are there and required bit is 0
                // we need to reset set bits
                ans += bit1;
            } else if(bit1 == 0 & bit > 0) {
                // some set bits required, so we can flip one reset bit to set
                ans += 1;
            }
        }

        return ans;
    }
}