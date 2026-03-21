class Solution {
    public int rob(int[] A) {
        
        HashMap<Integer, Integer> DP = new HashMap<>();
        return max(A, 0, false, DP);
    }

    int max(int A[], int start, boolean selected0, HashMap<Integer, Integer> DP) {
        int N = A.length;
        if(selected0 == true && start == N - 1) {
            // you can't selected last if you select 0th index
        }
        if(start == N) {
            return 0;
        }

        if(DP.containsKey(start)) {
            return DP.get(start);
        }

        int profit = 0;
        for(int i = start; i < N; i++) {
            profit = Math.max(profit, A[i] + max(A, i + 2, i == 0, DP)); // need to skip the adjacent one
        }

        DP.put(start, profit);

        return profit;
    }
}