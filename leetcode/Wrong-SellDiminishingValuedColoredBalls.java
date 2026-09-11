// https://leetcode.com/problems/sell-diminishing-valued-colored-balls/

// for example: [3, 5] and orders = 6.
// if we take 5 orers from 5 then it would 15 and remaining 1 would from 3 = 18
// but if we sell [5 + 4 + 3 + 2] + [3 + 2] = 19 

class Solution {

    static final int MOD = 1000000000 + 7;

    public int maxProfit(int[] inventory, int orders) {
        // greedy more number of balls more you will get paid
        Arrays.sort(inventory);

        int N = inventory.length;
        long ans = 0;
        int i = N - 1;
        while(orders > 0) {
            
            int t = inventory[i];

            if(t <= orders) {
                long cost = 1L * t * (t + 1) / 2;
                ans = (ans + cost) % MOD;

                orders -= t;
            } else {
                long cost1 = 1L * t * (t + 1) / 2;
                long cost2 = 1L* (t - orders) * (t - orders + 1) / 2;
                ans = (ans + cost1 - cost2) % MOD;

                orders = 0; // done
            }

            i--;
        }

        return Long.valueOf(ans).intValue();
    }
}