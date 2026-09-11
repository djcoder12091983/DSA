// https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
// TODO we need reset sum to 0 when deleting negative minimum value sum is still 0

class Solution {
    public int maximumSum(int[] A) {
        int N = A.length;
        int s = 0;
        int max = Integer.MIN_VALUE;
        int delete = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            s += A[i];
            delete = Math.min(delete, A[i]); // track minimum value in the subarray

            // we will try to maximize the sum by deleting minimum value in the subarray
            if(delete < 0) {
                max = Math.max(max, s - delete);
            } else {
                // if minimum value > 0 then no need to delete
                // coz it will decrease the subarray sum itself 
                max = Math.max(max, s);
            }
            
            
            if(s < 0 ) {
                // we will not carry forward to sum otherwise subarry sum will be decreased
                // reset all
                s = 0;
                delete = Integer.MAX_VALUE;
            }
        }

        return max;
    }
}