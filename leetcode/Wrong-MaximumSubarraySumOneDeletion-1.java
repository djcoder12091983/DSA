// https://leetcode.com/problems/maximum-subarray-sum-with-one-deletion/
// TODO -- wrong -- this has one corner case [1,-4,-5,-2,5,0,-1,2]

class Solution {
    public int maximumSum(int[] A) {
        int N = A.length;
        int s = 0;
        int max = Integer.MIN_VALUE;
        int delete = Integer.MAX_VALUE;

        // the idea is carry negative minimum value and we will reset sum 0
        // when current sum - negative minimum value < 0
        int i = -1;
        int j = 0;
        while(j < N) {
            s = s + A[j];
            delete = Math.min(delete, A[j]);
            
            int t = s;
            if(delete < 0) {
                // we found a negative value
                t -= delete;

                int len = j - i;
                if(len == 1) {
                    // if len is maximize if without deleting
                    // or else length would be 0
                    max = Math.max(max, t + delete);
                } else {
                    // maximize with delete
                    max = Math.max(max, t);
                }

            } else {
                // no delete so no need to check length
                max = Math.max(max, t);
            }

            if(t < 0) {
                // System.out.println("J: " + j);
                // anyways we can't carry forward the sum
                // coz even after deleting at most one negative value still the sum is < 0
                // reset all
                s = 0;
                i = j;
                delete = Integer.MAX_VALUE;
            }

            j++;
        }

        return max;
    }
}