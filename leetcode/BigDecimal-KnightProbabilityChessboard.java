// https://leetcode.com/problems/knight-probability-in-chessboard/
// TODO -- Solved using BigDecimal but Can we think better!

import java.math.*; // for bigdecimal

class Solution {

    int moves[][] = {
        {2, 1},
        {2, -1},
        {-2, 1},
        {-2, -1},
        {1, 2},
        {1, -2},
        {-1, 2},
        {-1, -2}
    };

    BigDecimal countmoves(int n, int k, int i, int j, Map<String, BigDecimal> DP) {
        
        if(i < 0 || i >= n || j < 0 || j >= n) {
            // stopped but outside the board
            return BigDecimal.ZERO;
        }
        
        if(k == 0) {
            // stopped but on board
            return BigDecimal.ONE;
        }

        String key = i + "-" + j + ":" + k;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        // eight possible moves
        BigDecimal ans = BigDecimal.ZERO;
        for(int m = 0; m < 8; m++) {
            ans = ans.add(countmoves(n, k - 1, i + moves[m][0], j + moves[m][1], DP));
        }

        DP.put(key, ans);

        return ans;
    }

    public double knightProbability(int n, int k, int row, int column) {

        Map<String, BigDecimal> DP = new HashMap<>();
        BigDecimal total = countmoves(n, k, row, column, DP);

        // System.out.println(total);

        if(total == BigDecimal.ZERO) {
            // no valid move
            return 0;
        }

        // probability computation using bigdecimal
        // TODO -- can we think better to compute
        BigDecimal base = new BigDecimal(8);
        BigDecimal p = base.pow(k);
        BigDecimal res = total.divide(p);
        return res.doubleValue();
    }
}