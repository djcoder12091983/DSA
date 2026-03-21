class Solution {
    public int rangeBitwiseAnd(int left, int right) {
        int ans = 0;
        for(int i = 0; i <= 30; i++) {
            // 0 to left, 0 to right then subtract
            int t1 = range(left, i);
            int t2 = range(right, i);

            int t = t2 - t1;
            if((left & (1 << i)) > 0) {
                // if ith position of left is set then add that bit because we subtract it already
                t += 1;
            }

            if(t == (right - left + 1)) {
                // all bits are set
                ans = ans + (1 << i); // AND will be 1
            }
        }

        return ans;
    }

    // find set bits in [0, A] for position idx
    int range(int A, int idx) {
        int x = 1 << (idx + 1);
        int y = 1 << idx;
        // per x bits we will have y set bits, like half 0 and half set bits
        int t = A/x * y; // total set bits till A/x
        int rem = A % x;
        // if remaining >= y then second half all bits will be 1
        if(rem >= y) {
            t = t + (rem - y + 1);
        }

        return t;
    }
}