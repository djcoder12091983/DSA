class Solution {

    // whether full tree to be explored or not
    // it's required when same subtree has been explored then
    // we will use precomputed value
    boolean subtree(long num, int bit, int h, long n) {
        while(h >= 0) {
            num += bit * (1L << h);
            h--;
            bit = 1 - bit; // toggle bit
        }

        return num <= n;
    }

    Map<String, Integer> DP;

    int count(long num, int bit, int h, long n) {

        if(h < 0 || num > n) {
            // height exausted or num itself
            return 0;
        }

        String key = bit + "-" + h; // bit with same height
        if(DP.containsKey(key) && subtree(num, bit, h, n)) {
            // already computed and full subtree can be explored
            return DP.get(key);
        }

        if(bit == 0) {
            if(num == n || h == 0) {
                return 1;
            }

            int res = count(num, 0, h - 1, n) + count(num, 1, h - 1, n);
            DP.put(key, res);
            return res;
        }

        num += (1L << h);
        if(num == n || h == 0) {
            return 1;
        }

        int res = count(num, 0, h - 1, n);
        DP.put(key, res);
        return res;
    }

    public int findIntegers(int n) {
        int h = (int)(Math.log(n) / Math.log(2));

        DP = new HashMap<>();
        return count(0, 0, h, n) + count(0, 1, h, n);
    }
}