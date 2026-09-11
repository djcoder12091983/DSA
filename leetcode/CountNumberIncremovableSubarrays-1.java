// https://leetcode.com/problems/count-the-number-of-incremovable-subarrays-i/
// input size is 50 so can be solved using BRUTE FORCE
// can we think it optimally for bigger input

class Solution {

    // check strictly increasing in a given range
    boolean check(int A[], int l, int r) {
        for(int i = l; i <= r - 1; i++) {
            if(A[i] >= A[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public int incremovableSubarrayCount(int[] A) {
        int N = A.length;
        // input size is 50 so can be solved using BRUTE FORCE
        // can we think it optimally for bigger input

        int c = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i; j < N; j++) {
                // for each subarray check whether array is strictly increasing
                if(check(A, 0, i - 1)) {
                    if(j + 1 < N) {
                        boolean flag = i - 1 < 0 || A[i - 1] < A[j + 1];
                        if(flag && check(A, j + 1, N - 1)) {
                            c++; // valid sequence
                        }
                    } else {
                        c++; // no right part
                    }
                }
            }
        }

        return c;
    }
}