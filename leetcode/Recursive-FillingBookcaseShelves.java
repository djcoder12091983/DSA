// https://leetcode.com/problems/filling-bookcase-shelves/
// TODO - TABULAR

class Solution {
    public int minHeightShelves(int[][] B, int W) {
        // now we will apply DP
        Map<Integer, Integer> DP = new HashMap<>();
        return arrange(B, 0, W, DP);
    }

    int arrange(int B[][], int idx, int W, Map<Integer, Integer> DP) {
        int N = B.length;
        if(idx == N) {
            return 0; // done, 0 height
        }

        if(DP.containsKey(idx)) {
            return DP.get(idx);
        }

        int tw = 0;
        // try to fit possible books and see which one gives minimum height
        int minHeight = Integer.MAX_VALUE;
        int h = 0;
        for(int i = idx; i < N; i++) {
            if(tw + B[i][0] > W) {
                break; // not more can fit 
            }

            tw = tw + B[i][0];
            h = Math.max(h, B[i][1]);

            // now take this arrange =ment a call recursively for remaining books
            minHeight = Math.min(minHeight, h + arrange(B, i + 1, W, DP));
        }

        DP.put(idx, minHeight);

        return minHeight;
    }
}