// https://leetcode.com/problems/champagne-tower/

class Solution {
    public double champagneTower(int P, int R, int C) {
        double DP[][] = new double[R + 1][R + 1];
        
        DP[0][0] = Math.max(0, P);
        for(int i = 1; i <= R; i++) {
            
            // we will split by half after filling by 1 cup
            DP[i][0] = Math.max(0, DP[i - 1][0] - 1) / 2.0; // first column
            
            int idx = Math.min(R, i);
            for(int j = 1; j < idx; j++) {
                // we will split by half after filling by 1 cup
                DP[i][j] = Math.max(0, DP[i - 1][j - 1] - 1) / 2.0 + Math.max(0, DP[i - 1][j] - 1) / 2.0;
            }
            
            // we will split by half after filling by 1 cup
            DP[i][idx] = Math.max(0, DP[i - 1][idx - 1] - 1) / 2.0; // last column
        }
        
        return Math.min(1, DP[R][C]);
    }
}