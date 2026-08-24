// https://leetcode.com/problems/decode-ways/description/

// TODO may need to optimize using TABULAR - top-down approach to reduce execution time

class Solution {
    // static final int MOD = 1000000000 + 7; // module for avoid overflow

    public int numDecodings(String S) {
        // now we will apply DP
        HashMap<Integer, Integer> DP = new HashMap<>();
        
        return count(S, 0, DP);    
    }

    // recurisvely solve
    int count(String S, int idx, HashMap<Integer, Integer> DP) {
        int N = S.length();
        if(idx == N) {
            return 1;
        }

        int x = S.charAt(idx) - '0';
        if(x == 0) {
            // not possible, can't start with 0
            return 0;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        int c = count(S, idx + 1, DP); // othen than '0' single digit consideration
        // now consider multiple digit and check boundary before making furtehr recursive call
        if(idx + 1 < N) {
            // combine two digits
            x = x * 10 + (S.charAt(idx +  1) - '0');
            if(x <= 26) {
                // valid number
                c += count(S, idx + 2, DP);
            }
        }

        // store for further use
        DP.put(idx, c);

        return c;
    }
}