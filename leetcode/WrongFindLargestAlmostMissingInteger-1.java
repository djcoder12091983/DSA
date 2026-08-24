// https://leetcode.com/problems/find-the-largest-almost-missing-integer/description/
// TODO need to check duplicate subarray count

class Solution {
    public int largestInteger(int[] A, int k) {
        // we will track every element k-size subarray frequency
        int N = A.length;

        if(k > N) {
            return -1; // not possible
        }

        HashMap<Integer, Integer> f = new HashMap<>();
        for(int i = 0; i < N; i++) {
            // see how many spaces there are on left and right side k-spaces
            // and take the minimum one
            int left = Math.min(i, k - 1), right = Math.min(N - 1 - i, k - 1);
            int c = 1 + Math.min(left, right);

            f.put(A[i], f.getOrDefault(A[i], 0) + c); // update k subarray frequency
        }

        int ans = -1;
        for(int i = 0; i < N; i++) {
            int c = f.get(A[i]);
            if(c == 1) {
                ans = Math.max(A[i], ans);
            }
        }

        return ans;
    }
}