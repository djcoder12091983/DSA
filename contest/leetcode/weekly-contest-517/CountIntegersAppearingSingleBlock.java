// https://leetcode.com/problems/count-integers-appearing-in-a-single-block/
// BRUTE-FORCE -- TODO can we think of optimal solution

class Solution {

    static int LIMIT = 100;
    
    public int countSpecialIntegers(int[] A) {
        // brute force
        int N = A.length;
        boolean V[] = new boolean[LIMIT + 1]; // as per input
        Arrays.fill(V, false);

        int ans = 0;
        for(int i = 0; i < N; i++) {
            if(V[A[i]]) {
                // already visited
                continue;
            }
            int t[] = new int[LIMIT]; // track index
            int k = 0;
            for(int j = 0; j < N; j++) {
                if(A[i] == A[j]) {
                    t[k++] = j;
                }
            }

            // now check they are contiguous or what
            boolean flag = true;
            for(int j = 0; j < k - 1; j++) {
                if(t[j] + 1 != t[j + 1]) {
                    // not contiguous
                    flag = false;
                    break;
                }
            }

            if(flag) {
                ans++;
            }

            V[A[i]] = true; // visited
        }

        return ans;
    }
}