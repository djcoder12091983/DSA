// https://leetcode.com/problems/number-of-subarrays-having-even-product/

class Solution {
    public long evenProduct(int[] A) {
        // we always conside the start point or end point to count subarray to avoid duplicates
        // first we will computer latest even index on right side or else we can think of on left side
        // so in that case we don't need to store suffix even index
        // so in case of left look up we need to consider subarray end index

        int N = A.length;
        int evenIdx = -1;
        long ans = 0;
        for(int i = 0; i < N; i++) {
            if(A[i] % 2 == 0) {
                // track latest even index
                evenIdx = i;
            }

            // compute based on latest even index on left
            ans += evenIdx + 1;
        }

        return ans;
    }
}