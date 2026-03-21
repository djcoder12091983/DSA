class Solution {
    public int maxProfit(int[] A) {
        
        // 0 - i maximum profit then i + 1 to N - 1 maximum profit

        int n = A.length;
        // consider ith sell point so find prefix min to find buy point to maximize the profit
        int maxprofit1[] = new int[n];
        maxprofit1[0] = 0;
        int minbuy = A[0];
        for(int i = 1; i < n; i++) {
            minbuy = Math.min(minbuy, A[i]);
            maxprofit1[i] = Math.max(maxprofit1[0], A[i] - minbuy);
        }

        // consider ith buy point so find suffix max to find sell point to maximize profit
        int maxprofit2[] = new int[n];
        maxprofit2[n - 1] = 0;
        int maxsell = A[n  - 1];
        for(int i = n - 2; i >= 0; i--) {
            maxsell = Math.max(maxsell, A[i]);
            maxprofit2[i] = Math.max(maxprofit2[i + 1], maxsell - A[i]);
        }

        // now check @ most two halves
        int ans = Math.max(maxprofit1[n - 1], maxprofit2[0]);
        for(int i = 1; i < n; i++) {
            ans = Math.max(ans, maxprofit1[i - 1] + maxprofit2[i]);
        }

        return ans;
    }
}