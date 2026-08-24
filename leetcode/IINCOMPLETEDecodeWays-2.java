// https://leetcode.com/problems/decode-ways-ii/

class Solution {
    static final int MOD = 1000000000 + 7; // module for avoid overflow

    public int numDecodings(String S) {
        // now we will apply DP
        HashMap<Integer, Long> DP = new HashMap<>();
        
        return Long.valueOf(count(S, 0, DP)).intValue();    
    }

    // recurisvely solve
    long count(String S, int idx, HashMap<Integer, Long> DP) {
        int N = S.length();
        if(idx == N) {
            return 1;
        }

        char ch = S.charAt(idx);
        if(ch == '0') {
            // not possible, can't start with 0
            return 0;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        // this several conditions seem to be bit MESSY, handling different edge case
        // TODO may need to think how to simply handling logic for edge cases

        long c = 0;
        // will separate out two logic, if it's '*'
        // TODO other than separating out can we think of generalize the logic simpler way
        if(ch == '*') {
            // simulate from 1-9
            for(int i = 0; i < 9; i++) {
                c += cont(S, idx + 1, DP);
            }

            // TODO need to complete this coding with MESSY logic
            // FOUND some simple logic to handle edge cases without writing MESSY code

        } else {
            int x = ch - '0';
            c += count(S, idx + 1, DP); // othen than '0' single digit consideration
            // now consider multiple digit and check boundary before making furtehr recursive call
            if(idx + 1 < N) {
                // combine two digits
                ch = S.charAt(idx +  1);

                // will separate out two logic, if it's '*'
                // TODO other than separating out can we think of generalize the logic simpler way
                if(ch == '*') {
                    int l = 0;
                    if(x == 1) {
                        l = 9; // 11 - 19
                    } else {
                        l = 6; // 21 - 26
                    }
                    for(int i = 0; i < l; i++) {
                        // repetitve calls for '*'
                        c += count(S, idx + 2, DP)
                    }
                } else {
                    // (normal flow)
                    x = x * 10 + (ch - '0');
                    if(x <= 26) {
                        // valid number
                        c += count(S, idx + 2, DP);
                    }
                }
            }
        }

        // store for further use
        DP.put(idx, c);

        return c;
    }
}