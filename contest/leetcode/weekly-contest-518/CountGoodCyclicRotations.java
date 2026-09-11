// https://leetcode.com/problems/count-good-cyclic-rotations/

class Solution {
    public int countGoodRotations(int[] A) {
        int N = A.length;
        long P[] = new long[N + 1];

        P[0] = 0;
        for(int i = 1; i <= N; i++) {
            P[i] = P[i - 1] + A[i - 1];
        }

        int i = 0;
        int ans = 0;
        while(i < N) {
            // rotate i times and see the sums
            int mid = N / 2;
            long left;
            if(i <= mid) {
                left = P[i + mid] - P[i];
            } else {
                left = P[N] - P[i] + P[mid - N + i];
            }

            long right = right = P[N] - left;
            if(left > right) {
                ans++;
            }

            i++;
        }

        return ans;
    }
}