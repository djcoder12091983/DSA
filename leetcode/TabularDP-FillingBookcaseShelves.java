// https://leetcode.com/problems/filling-bookcase-shelves/

class Solution {
    public int minHeightShelves(int[][] B, int W) {
        int N = B.length;
        int DP[] = new int[N + 1];
        DP[0] = 0;

        for(int i = 1; i <= N; i++) {

            int tw = 0;
            int minHeight = Integer.MAX_VALUE;
            int h = 0;
            for(int j = i; j > 0; j--) {
                if(tw + B[j - 1][0] > W) {
                    break; // not more book will fit 
                }

                tw = tw + B[j - 1][0];
                h = Math.max(h, B[j - 1][1]);
                minHeight = Math.min(minHeight, h + DP[j - 1]);
            }

            DP[i] = minHeight;
        }

        return DP[N];
    }
}