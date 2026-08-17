// https://leetcode.com/problems/minimum-size-subarray-sum/

class Solution {
    public int minSubArrayLen(int T, int[] A) {
        
        int i = 0, j = 0;
        int N = A.length;
        int sum = 0;
        int minlen = N + 1;
        // 2P as long as sum < T expand window when sum decreases then shrink
        // because as the numbers > 0 so without shrinking sum >= T length will be increasing
        // but we need to find minimum length
        while(j < N) {
            int t = sum + A[j];
            if(t >= T) {
                minlen = Math.min(minlen, j - i + 1);
                sum -= A[i]; // window shrink
                i++;
                if(i > j) {
                    // handle edge case
                    j++;
                }
            } else {
                // window expand
                sum = t;
                j++;
            }
        }

        if(minlen == N + 1) {
            // no such subarray found
            return 0;
        }

        return minlen;
    }
}