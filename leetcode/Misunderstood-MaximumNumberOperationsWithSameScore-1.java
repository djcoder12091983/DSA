// https://leetcode.com/problems/maximum-number-of-operations-with-the-same-score-i/
// Misunderstood -- thsi logic will work if any two elements deleted and each time score remains same

class Solution {
    public int maxOperations(int[] A) {
        // maximum pair frequency with distinct pairs
        // distinct pairs meaning two index are distinct

        Map<Integer, Set<Integer>> pairs = new HashMap<>();
        int N = A.length;

        int ans = 0;

        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                int s = A[i] + A[j];
                Set<Integer> idx = pairs.get(s);
                if(idx == null) {
                    idx = new HashSet<>();
                    pairs.put(s, idx);
                }

                if(!idx.contains(i) && !idx.contains(j)) {
                    // both are different
                    idx.add(i);
                    idx.add(j);
                }

                ans = Math.max(ans, idx.size() / 2);
            }
        }

        // System.out.println(pairs);

        return ans;
    }
}