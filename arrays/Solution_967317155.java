class Solution {
    public int minEatingSpeed(int[] piles, int hours) {

        long t = 0;
        int n = piles.length;
        long max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            t = t + piles[i];
            max = Math.max(max, piles[i]);
        }

        long l = t/hours;
        if(l == 0) {
            l = 1;
        }
        long h = max;

        long ans = h;

        while(l <= h) {
            long mid = (l + h)/2;
            long required = 0;
            //System.out.println(l + " " + h + " " + mid);
            for(int i = 0; i < n; i++) {
                required += piles[i]/mid;
                if(piles[i] % mid != 0) {
                    required++;
                }
            }
            //System.out.println(l + " " + h + " " + mid + " " + required);
            if(required <= hours) {
                ans = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        
        return (int)ans;
    }
}