// https://leetcode.com/problems/sum-of-beauty-in-the-array/

class Solution {
    public int sumOfBeauties(int[] A) {
        // prefix maximum and suffix min will work
        int N = A.length;
        int P[] = new int[N];
        P[0] = A[0];
        for(int i = 1; i < N; i++) {
            P[i] =  Math.max(P[i - 1], A[i]);
        }

        int S[] = new int[N];
        S[N - 1] = A[N - 1];
        for(int i = N - 2; i >= 0; i--) {
            S[i] = Math.min(S[i + 1], A[i]);
        }

        int beauty = 0;
        for(int i = 1; i <= N - 2; i++) {
            int b = 0;
            if(A[i] > P[i - 1] && A[i] < S[i + 1]) {
                b = 2;
            } else if(A[i] > A[i - 1] && A[i] < A[i + 1]) {
                b = 1;
            }
            beauty += b;
        }

        return beauty;
    }
}