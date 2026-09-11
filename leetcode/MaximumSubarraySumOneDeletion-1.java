// https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
// TODO we may need to think of using 2P and prefix, this logic is good but playing with index seems to be bit messy
// we may need to think better

class Solution {
    public int maximumSum(int[] A) {
        int N = A.length;
        // we will use the idea like for every negative number we will see
        // if we delete then on left side and and right what maximum sum we can get
        // to do it will use prefix sum and prefix min and suffix max on prefix sum
        int S[] = new int[N + 1];
        S[0] = 0;
        int PM[] = new int[N + 1];
        PM[0] = 0;
        for(int i = 0; i < N; i++) {
            S[i + 1] = S[i] + A[i];
            if(S[i + 1] < S[PM[i]]) {
                PM[i + 1] = i + 1;
            } else {
                PM[i + 1] = PM[i];
            }
        }
        int SM[] = new int[N + 1];
        SM[N] = N;
        for(int i = N - 1; i >= 0; i--) {
            if(S[i] > S[SM[i + 1]]) {
                SM[i] = i;
            } else {
                SM[i] = SM[i + 1];
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= N; i++) {
            if(A[i - 1] < 0) {
                int sum = 0;
                int len = 0;
                // left part
                int idx = PM[i - 1];
                len += i - 1 - idx;
                sum += S[i - 1] - S[idx];

                // right part
                idx = SM[i];
                len += idx - i;
                sum += S[idx] - S[i];

                if(len > 0) {
                    max = Math.max(max, sum);
                } else {
                    // no positive element
                    max = Math.max(max, A[i - 1]);
                }
            }
        }

        if(max == Integer.MIN_VALUE) {
            // as there is no negative numberwe can whole sum is the answer
            return S[N];
        }

        return max;
    }
}