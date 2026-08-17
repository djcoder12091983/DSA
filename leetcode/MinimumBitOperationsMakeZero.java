// https://leetcode.com/problems/minimum-one-bit-operations-to-make-integers-zero/description/

class Solution {
    public int minimumOneBitOperations(int n) {

        if(n == 0) {
            // edge case
            return 0;
        }

        // this is reverse problem "converting 0 to reach some N number using just one bit difference"
        // every consecutive number will have exactly one bit difference

        int pos = 0;
        int t = n;
        // find left most bit position
        while(t > 0) {
            t = t >> 1;
            pos++;
        }

        // this pattern will help to find which slot i need to choose to reach the element
        int pattern[] = new int[]{0, 1, 1, 0};

        int ops = 1 << (pos - 1);
        int slot = 1;
        for(int i = pos - 2; i >= 0; i--) {
            int bit = n & (1 << i);
            // visualizing it as binary tree
            int left = slot << 1; // odd leaf
            if(pattern[left & 3] == 0) {
                // 0 1 pattern
                if(bit > 0) {
                    ops += (1 << i); // second block 
                    slot = left + 1;
                } else {
                    slot = left;
                }
            } else {
                // 1 0 apttern
                if(bit == 0) {
                    // second block
                    ops += (1 << i);
                    slot = left + 1;
                } else {
                    slot = left;
                }
            }
        }

        return ops;
    }
}