// https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/

class Solution {
    public int preimageSizeFZF(int k) {
        if(k <= 1) {
            return 5;
        }

        long start = 5;
        Map<Long, Integer> DP = new HashMap<>();
        DP.put(start, 1);

        int c5 = 1; // factors 5
        // TODO -- may be it will be TLE
        while(c5 < k) {
            start += 5;
            long x = start / 5;

            int t = DP.getOrDefault(x, 0) + 1;
            DP.put(start, t);
            
            c5 += t;
        }

        // the answer would be either 5 or 0
        if(c5 == k) {
            // solution exists
            return 5;
        } else {
            return 0;
        }
    }
}