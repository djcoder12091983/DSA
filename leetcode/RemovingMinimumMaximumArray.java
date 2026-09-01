// https://leetcode.com/problems/removing-minimum-and-maximum-from-array

class Solution {
    public int minimumDeletions(int[] A) {
        int minIdx = 0;
        int min = A[0];
        int N = A.length;
        for(int i = 1; i < N; i++) {
            if(A[i] < min) {
                min = A[i];
                minIdx = i;
            }
        }

        int maxIdx = 0;
        int max = A[0];
        for(int i = 1; i < N; i++) {
            if(A[i] > max) {
                max = A[i];
                maxIdx = i;
            }
        }

        int p1 = Math.min(minIdx, maxIdx);
        int p2 = Math.max(minIdx, maxIdx);
        // now we will explore possible deletions
        int ans = p2 + 1; // take all from front
        ans = Math.min(ans, N - p1); // take all from back
        ans = Math.min(ans, p1 + 1 + N - p2); // take from front and back both

        return ans;
    }
}