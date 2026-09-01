// https://leetcode.com/problems/find-the-index-of-permutation/

class Solution {

    static final int MOD = 1000000000 + 7;

    long[] fact(int N) {
        long f[] = new long[N + 1];
        f[0] = 1;
        for(int i = 1; i <= N; i++) {
            f[i] = (f[i - 1] * i) % MOD;
        }

        return f;
    }

    public int getPermutationIndex(int[] A) {
        // first try BRUTE-FORCE approach at least then it does not work for 100000
        // then we can think of some optimization
        int N = A.length;
        long f[] = fact(N);

        long ans = 0;
        for(int i = 0; i < N; i++) {

            // how many digits are lesser than A[i] on right side
            // those many formed permutations we need skip and add to answer 
            // TODO this loop can be optpimized
            int c = 0;
            for(int j = i + 1; j < N; j++) {
                if(A[j] < A[i]) {
                    c++;
                }
            }

            ans = (ans + (c * f[N - 1 - i]) % MOD) % MOD; // contribute answer
        }

        // we have skipped all permutations now next permutation is the answer
        // ans = (ans + 1) % MOD; // This is required when rank is 1 based

        return Long.valueOf(ans).intValue();
    }
}