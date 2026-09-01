// https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
// TODO the code is written based on some conditions, if we could use prefix and suffix with N + 1 size
// then it would it would be easy to write clean code without conditions to handle edge boundary

class Solution {
    public int maxScore(int[] A, int k) {
        int N = A.length;
        int S[] = new int[N];

        // suffix sum
        S[N - 1] = A[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            S[i] = S[i + 1] + A[i];
        }

        int left = 0;
        int ans = 0; // all positives
        int p = 0; // we will pick from left and remaining from right
        while(p <= k) {
            int right = 0;
            if(N - k + p < N) {
                right = S[N - k + p];
            }

            ans = Math.max(ans, left + right);

            if(p < k) {
                left += A[p];
            }
            p++;
        }

        return ans;
    }
}