// https://leetcode.com/problems/distinct-subsequences-ii

class Solution {

    static final int MOD = 1000000000 + 7;

    public int distinctSubseqII(String s) {
        int N = s.length();
        long DP[] = new long[N + 1];
        DP[0] = 1; // empty subsequence

        for(int i = 1; i <= N; i++) {
            char x = s.charAt(i - 1);

            // if it's a duplicate one
            int j  = i - 1;
            // TODO we can avoid this loop, we can track last occurance of current character
            while(j > 0) {
                char y = s.charAt(j - 1);
                if(x == y) {
                    // found
                    break;
                }

                j--;
            }

            DP[i] = (2 * DP[i - 1]) % MOD;
            if(j > 0) {
                // duplicate found, substract last occurance computed contribution
                DP[i] = (DP[i] - DP[j - 1] + MOD) % MOD; // duplicate adjustment
            }
        }

        // we need exclude non empty subsequence
        return Long.valueOf((DP[N] - 1 + MOD) % MOD).intValue();
    }
}