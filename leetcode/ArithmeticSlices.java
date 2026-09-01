// https://leetcode.com/problems/arithmetic-slices/

class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int N = A.length;

        if(N < 3) {
            // at least 3 elements should be there
            return 0;
        }

        int i = 0, j = 2;
        int ans = 0;
        while(j < N) {
            int x = A[j - 1] - A[j - 2];
            int y = A[j] - A[j - 1];
            if(x == y) {
                j++; // continue sequene
            } else {
                // sequence break
                int c = j - i;
                ans = ans + c*(c+1)/2 - 2*c + 1;

                // reset pointers
                i = j - 1;
                j = j + 1;
            }
        }

        // last valid sequence
        if(A[j - 1] - A[j - 2] == A[j - 2] - A[j - 3]) {
            int c = j - i;
            ans = ans + c*(c+1)/2 - 2*c + 1;
        }

        return ans;
    }
}