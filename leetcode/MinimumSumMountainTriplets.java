// https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i/description/

class Solution {
    public int minimumSum(int[] A) {
        // we will go with one point then see on left and right is there any element lesser on both side or not
        // if both are lesser then we will go with minimum ones to find minimized sum

        int N = A.length;
        int ans = Integer.MAX_VALUE;
        for(int i = 1; i < N - 1; i++) {
            // mountain point
            int min1 = Integer.MAX_VALUE;
            // left side
            for(int j = 0; j < i; j++) {
                if(A[j] < A[i]) {
                    min1 = Math.min(min1, A[j]);
                }
            }

            // right side
            int min2 = Integer.MAX_VALUE;
            for(int j = i + 1; j < N; j++) {
                if(A[j] < A[i]) {
                    min2 = Math.min(min2, A[j]);
                }
            }

            if(min1 != Integer.MAX_VALUE && min2 != Integer.MAX_VALUE) {
                // triplet possible
                ans = Math.min(ans, A[i] + min1 + min2);
            }
        }

        if(ans == Integer.MAX_VALUE) {
            return -1; // not possible
        }

        return ans;
    }
}