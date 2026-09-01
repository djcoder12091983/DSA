// https://leetcode.com/problems/find-the-distinct-difference-array/

class Solution {
    public int[] distinctDifferenceArray(int[] A) {
        
        int N = A.length;
        
        // we will solve it in O(N) time
        Map<Integer, Integer> suffix = new HashMap<>();
        for(int i = N - 1; i >=0; i--) {
            suffix.put(A[i], suffix.getOrDefault(A[i], 0) + 1);
        }

        int ans[] = new int[N];
        Set<Integer> prefix = new HashSet<>();
        for(int i = 0; i < N; i++) {
            prefix.add(A[i]);
            suffix.put(A[i], suffix.get(A[i]) - 1);
            if(suffix.get(A[i]) == 0) {
                suffix.remove(A[i]);
            }

            ans[i] = prefix.size() - suffix.size();
        }

        return ans;
    }
}