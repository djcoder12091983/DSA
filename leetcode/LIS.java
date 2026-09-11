// https://leetcode.com/problems/longest-increasing-subsequence/
// TODO may need to solve using Nlog(N)

class Solution {

    // idx indicates idx-th element is included
    int lis(int A[], int idx, Map<Integer, Integer> DP) {

        if(DP.containsKey(idx)) {
            // already computed
            return DP.get(idx);
        }

        int N = A.length;
        int max = 0;
        for(int i = idx + 1; i < N; i++) {
            if(A[i] > A[idx]) {
                // valid subsequence
                max = Math.max(max, 1 + lis(A, i, DP));
            }
        }

        DP.put(idx, max);

        return max;
    }

    public int lengthOfLIS(int[] A) {
        int max = 0;
        int N = A.length;
        
        // TODO now we will apply DP
        Map<Integer, Integer> DP = new HashMap<>();
        
        for(int i = 0; i < N; i++) {
            max = Math.max(max, 1 + lis(A, i, DP));
        }

        return max;
    }
}