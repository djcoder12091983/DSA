// https://leetcode.com/problems/maximize-the-profit-as-the-salesman/

class Solution {
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        // we will apply greedy + DP
        // we will sort by start and traverse from opposite side and compute maximum profit for each point

        Collections.sort(offers, (x, y) -> x.get(0) - y.get(0));

        int N = offers.size();
        int DP[] = new int[N];
        DP[N - 1] = offers.get(N - 1).get(2); // gold
        for(int i = N - 2; i >= 0; i--) {

            // we will binary search to find valid offers where start > current end point
            // TODO need to validate whether any point is valid like start > current end point
            // or the closer one
            int idx = -1;
            int l = i + 1, r = N - 1;
            int t = offers.get(i).get(1); // current end point
            while(l <= r) {
                int mid = (l + r) / 2;
                if(offers.get(mid).get(0) > t) {
                    // find closer one
                    idx = mid;
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }

            // update current maximum value
            if(idx != -1) {
                DP[i] = Math.max(DP[i + 1], offers.get(i).get(2) + DP[idx]);
            } else {
                DP[i] = Math.max(DP[i + 1], offers.get(i).get(2));
            }
        }

        return DP[0];
    }
}