// https://leetcode.com/problems/count-commas-in-range-ii

class Solution {
    public long countCommas(long n) {
        long start = 1000; // where it will start from
        long ans = 0;
        int commas = 1;
        while(start <= n) {
            long t = start * 1000 - 1;
            t = Math.min(n, t);

            ans = ans + (t - start + 1) * commas; // this many commas

            start = t + 1; // next start point
            commas++;
        }

        return ans;
    }
}