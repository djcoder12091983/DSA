class Solution {

    public long countDistinct(long n) {
        // we will generate numbers using permutation tree
        // by avoiding 0 digit

        long d[] = new long[16]; // maximum limit 16 digit
        long t = n;
        int l = 0;
        while(t > 0) {
            long x = t % 10;
            d[l++] = x;

            t = t / 10;
        }

        long p9 = 9;
        long ans = 0;
        // first we will generate all 0 free numbers < digit count
        for(int i = 1; i < l; i++) {
            ans += p9;
            p9 = p9 * 9;
        }

        // now we will count same length numbers without zero
        boolean zero = false;
        for(int i = l - 1; i >= 0; i--) {
            if(d[i] == 0) {
                // we can proceed any more
                // because we will end up generating 0 digit numbers
                zero = true;
                break;
            }
			
            p9 = p9 / 9;
            ans += (d[i] - 1) * p9;
        }

        if(!zero) {
            // we can include the number as part of answer
            ans++;
        }

        return ans;
    }
}