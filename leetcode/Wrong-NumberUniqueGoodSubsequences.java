// https://leetcode.com/problems/number-of-unique-good-subsequences/
// TODO -- Fix while discarding leading zero subsequence it counts duplicate delete

class Solution {

    static final int MOD = 1000000000 + 7;
    
    // populate DP given binary string, generate unique subsequence
    long[] populateDP(String s) {
        int N = s.length();
        long DP[] = new long[N + 1];
        DP[0] = 1; // empty subsequence

        // last occurance of 0 and 1 
        int last1 = 0, last0 = 0;

        for(int i = 1; i <= N; i++) {
            char x = s.charAt(i - 1);

            DP[i] = (2 * DP[i - 1]) % MOD;
            // duplicate found, substract last occurance computed contribution
            if(x == '0') {
                if(last0 > 0) {
                    DP[i] = (DP[i] - DP[last0 - 1] + MOD) % MOD; // duplicate adjustment
                }
                last0 = i;
            } else {
                if(last1 > 0) {
                    DP[i] = (DP[i] - DP[last1 - 1] + MOD) % MOD; // duplicate adjustment
                }
                last1 = i;
            }
        }

        return DP;
    }

    public int numberOfUniqueGoodSubsequences(String binary) {
        
        // populate DP from right to left to discard leading zero substring
        String reverse = new StringBuilder(binary).reverse().toString();
        long DP[] = populateDP(reverse);
        // now discard leading zero subsequence
        long discard = 0;
        int N = reverse.length();
        for(int i = 2; i <= N; i++) {
            char x = reverse.charAt(i - 1);
            if(x == '0') {
                discard = (discard + DP[i - 1]) % MOD;
            }
        }

        // long total = (DP[N] - 1 + MOD) % MOD; // exclude empty subsequence
        long ans = (DP[N] - discard + MOD) % MOD;
        return Long.valueOf(ans).intValue();
    }
}