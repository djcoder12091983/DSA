// https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
// INCOMPLETE - TODO need to complete!

class Solution {
    public int maximumSum(int[] A) {
        int N = A.length;
        // we will use the idea like for every negative number we will see
        // if we delete then on left side and and right what maximum sum we can get
        // to do it will use prefix sum and prefix min and suffix max on prefix sum
        int S[] = new int[N];
        S[0] = A[0];
        int PM[] = new int[N];
        PM[0] = 0;
        for(int i = 1; i < N; i++) {
            S[i] = S[i - 1] + A[i];
            if(S[i] < S[PM[i - 1]]) {
                PM[i] = i;
            } else {
                PM[i] = PM[i - 1];
            }
        }
        int SM[] = new int[N];
        SM[N - 1] = N - 1;
        for(int i = N - 2; i>= 0; i--) {
            if(S[i] > S[SM[i + 1]]) {
                SM[i] = i;
            } else {
                SM[i] = SM[i + 1];
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            if(A[i] < 0) {
                int sum = 0;
                int len = 0;
                // left part
                if(i > 0) {
                    int idx = PM[i - 1];
                    len += i - 1 - idx;
                    sum += S[i - 1] - S[idx];
                }

                // right part
                int idx = SM[i];
                len += idx - i;
                sum += S[idx] - S[i];

                if(len > 0) {
                    max = Math.max(max, sum);
                } else {
                    // no positive element
                    max = Math.max(max, A[i]);
                }
            }
        }

        return max;
    }
}