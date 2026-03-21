class Solution {
    public int findDuplicate(int[] A) {
        int i = 0;
        int N = A.length;
        while(i < N) {
            if(A[i] == i + 1) {
                // in place
                i++;
            } else {
                if(A[A[i] - 1] == A[i]) {
                    // already duplicate element is placed earlier
                    return A[i];
                }
                // not in place need to place in original position
                int t = A[i];
                A[i] = A[A[i] - 1];
                A[t - 1] = t;
            }
        }

        // always duplicate element is found
        return -1; // this won't happen
    }
}