// https://leetcode.com/problems/decode-ways-ii/

// TODO may need to optimize using TABULAR - top-down approach to reduce execution time

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

        // prepare possible combinations accordingly as per '*' or non '*'
        int list1[] = null;
        int list2[] = null;
        // list 1
        if(ch == '*') {
            list1 = new int[9];
            for(int i = 1; i <= 9; i++) {
                list1[i - 1] = i;
            }
        } else {
            list1 = new int[1];
            list1[0] = ch - '0';
        }
        // next character
        if(idx + 1 < N) {
            ch = S.charAt(idx + 1);
            if(ch == '*') {
                list2 = new int[9];
                for(int i = 1; i <= 9; i++) {
                    list2[i - 1] = i;
                }
            } else {
                list2 = new int[1];
                list2[0] = ch - '0';
            }   
        } else {
            list2 = new int[0]; 
        }

        long c = 0;
        
        // now work on possible combinations if it contains '*'
        int l1 = list1.length, l2 = list2.length;
        for(int i = 0; i < l1; i++) {
            c = (c + count(S, idx + 1, DP)) % MOD; // othen than '0' single digit consideration
            for(int j = 0; j < l2; j++) {
                // now consider multiple digit and check boundary before making furtehr recursive call
                int x = list1[i];
                // combine two digits
                x = x * 10 + (list2[j]);
                if(x <= 26) {
                    // valid number
                    c = (c + count(S, idx + 2, DP)) % MOD;
                }
            }
        }

        // store for further use
        DP.put(idx, c);

        return c;
    }
}