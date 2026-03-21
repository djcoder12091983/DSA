class Solution {
    public int rob(int[] A) {
        
        HashMap<String, Integer> DP = new HashMap<>();
        return max(A, 0, false, DP);
    }

    int max(int A[], int start, boolean selected0, HashMap<String, Integer> DP) {
        int N = A.length;
        if(selected0 == true && start == N - 1) {
            // you can't selected last if you select 0th index
            return 0;
        }
        if(start == N) {
            return 0;
        }

        String key = start + "#" + selected0;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        int profit = 0;
        if(selected0 == true) {
            N = N - 1; // you can't selected last if you select 0th index
        }
        for(int i = start; i < N; i++) {
            profit = Math.max(profit, A[i] + max(A, i + 2, (i == 0) | selected0, DP)); // need to skip the adjacent one
        }

        DP.put(key, profit);

        return profit;
    }
}