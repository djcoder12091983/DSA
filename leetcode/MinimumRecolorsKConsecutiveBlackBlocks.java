// https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks/

class Solution {
    public int minimumRecolors(String B, int k) {
        // fixed size slide window of k and see which block gives minimum W
        // so to minimize W to B
        int i = 0, j = 0;
        int w = 0;
        while(j < k) {
            if(B.charAt(j) == 'W') {
                w++;
            }

            j++;
        }
        int ans = w; // first block

        int N = B.length();
        while(j < N) {
            if(B.charAt(i) == 'W') {
                w--;
            }
            if(B.charAt(j) == 'W') {
                w++;
            }

            i++;
            j++;

            ans = Math.min(ans, w);
        }

        return ans;
    }
}