class Solution {
    HashMap<Integer, Long> dp = new HashMap<>();
    public int jump(int[] A) {
        return (int)min(A, 0);
    }

    long min(int[] A, int idx) {
        int N = A.length;
        if(idx == N - 1) {
            return 0; // we are done
        }

        // DP check
        if(dp.containsKey(idx)) {
            return dp.get(idx);
        }

        // check all possibilities
        long jmp = Integer.MAX_VALUE; // to avoid overflow issue
        for(int i = idx + 1; i <= Math.min(N - 1, idx + A[idx]); i++) {
            if(A[i] > 0 || i == N - 1) {
                // jump possible
                jmp = Math.min(jmp, 1 + min(A, i));
            }
        }

        // dp update
        dp.put(idx, jmp);

        return jmp;
    }
}