// https://leetcode.com/problems/count-alternating-subarrays/

class Solution {
    public long countAlternatingSubarrays(int[] A) {
        // first we will find sequence of alternates
        // then we will contribute number of alternate subarrys from that sequence

        long c = 0;
        int N = A.length;
        int t = 1; // minimum alternate sequence
        for(int i = 0; i < N - 1; i++) {
            if(A[i] != A[i + 1]) {
                t++;
            } else {
                // reset and contribute to global count
                // if there are x binary bits are there and it's a laternate sequence
                // then we can say all the subarrays are alternate sequences
                c += 1L*t*(t + 1) / 2;
                t = 1;
            }
        }

        // last alternate sequence contribution
        c += 1L*t*(t + 1) / 2;

        return c;
    }
}