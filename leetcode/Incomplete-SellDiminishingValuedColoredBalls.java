// https://leetcode.com/problems/sell-diminishing-valued-colored-balls/
// INCOMPLETE -- Seems to be TICKY to implement the IDEA!

class Solution {

    static final int MOD = 1000000000 + 7;

    public int maxProfit(int[] inventory, int orders) {
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int x : inventory) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        // greedy more number of balls more you will get paid
        List<Integer> values = map.values();
        Collections.sort(values, Collections.reverseOrder());

        int N = values.size();
        long ans = 0;
        for(int i = 0; i < N - 1; i++) {
            int v1 = values.get(i);
            int v2 = values.get(i + 1);
			
			// TODO need to rethink the implementation here!

            int f = map.get(x);

            int available = (v1 - v2) * f;
            // TODO can we generalize the code
            if(orders >= available) {
                // easy to compute
                long cost1 = 1L * v1 * (v1 + 1) / 2;
                long cost2 = 1L * v2 * (v2 + 1) / 2;
                ans = (ans + ((cost1 - cost2) * f) % MOD) % MOD;

                orders -= available;
            } else {
                int t = orders / f;
                long cost1 = 1L * v1 * (v1 + 1) / 2;
                long cost2 = 1L * (v1 - t) * (v1 - t + 1) / 2;

                ans = (ans + ((cost1 - cost2) * f) % MOD) % MOD;
                
                int rem = orders % f;
                ans = (ans + ((v1 - t) * rem) % MOD) % MOD;

                orders = 0; // we are done
            }

            if(orders == 0) {
                // we are done
                break;
            }
        }

        // still orders > 0, last elements we will use
        if(orders > 0) {
			// NEED to work!
        }

        return Long.valueOf(ans).intValue();
    }
}