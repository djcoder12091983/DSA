// https://leetcode.com/problems/count-number-of-texts/
// need to use TABULAR top-down approach, to reduce execution time

class Solution {

    static final int MOD = 1000000000 + 7; // module for avoid overflow
    // static final int press[10] = {0, 0, 3, 3, 3, 3, 3, 4, 3, 4};

    public int countTexts(String M) {
        // now we will apply DP
        HashMap<Integer, Long> DP = new HashMap<>();
        return Long.valueOf(count(M, 0, DP)).intValue();
    }

    // recurisvely solve
    long count(String M, int idx, HashMap<Integer, Long> DP) {
        int N = M.length();
        if(idx == N) {
            return 1;
        }

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        int x = M.charAt(idx) - '0';
        // this limit will help pressing a digit can lead how much possibilities
        int limit = 3;
        if(x == 7 || x == 9) {
            limit = 4;
        }

        int i = idx;
        long c = 0;
        while(i < N && i < idx + limit) {
            int y = M.charAt(i) - '0';
            if(x != y) {
                // different character
                break;
            }

            // recurisve call for each possibilities
            c = (c + count(M, i + 1, DP)) % MOD;
            
            i++;
        }

        // store for further use
        DP.put(idx, c);

        return c;
    }
}