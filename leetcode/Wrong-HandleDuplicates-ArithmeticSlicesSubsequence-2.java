// https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
// TODO: Handle Duplicates

class Solution {

    int sequence(int A[], int idx, int prevIdx, int gap) {
        int N = A.length;
        if(idx == N) {
            return 0;
        }

        int i = idx;
        while(i < N) {
            if(A[prevIdx] + gap == A[i]) {
                break;
            }

            i++;
        }

        if(i < N) {
            return 1 + sequence(A, i + 1, i, gap);
        } else {
            return 0;
        }
    }

    public int numberOfArithmeticSlices(int[] A) {
        // pair to start with gap
        int N = A.length;

        int ans  = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int gap = A[j] - A[i];

                int len = 2 + sequence(A, j + 1, j, gap);
                if(len >= 3) {
                    ans += len - 3 + 1;
                }
            }
        }

        return ans;
    }
}