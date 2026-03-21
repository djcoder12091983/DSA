public class Solution {
    static int MIN = Integer.MIN_VALUE;
    
    int solve(ArrayList<ArrayList<Integer>> A, int l, int r, int N, int M, int DP[][]) {
        int x = A.get(l).get(r);
        if(l == N - 1 && r == M - 1) {
            // base case
            if(x >= 0) {
                return 0;
            } else {
                return x; // negative, return as it is
            }
        }
        if(DP[l][r] != MIN) {
            // already computed
            return DP[l][r];
        }
        // recursively solve
        int max = MIN;
        if(l + 1 < N) {
            int h = x + solve(A, l + 1, r, N, M, DP);
            if(h >= 0) {
                h = 0;
            }
            max = Math.max(max, h);
        }
        if(r + 1 < M) {
            int h = x + solve(A, l, r + 1, N, M, DP);
            if(h >= 0) {
                h = 0;
            }
            max = Math.max(max, h);
        }
        DP[l][r] = max;
        return max;
    }
    
    public int calculateMinimumHP(ArrayList<ArrayList<Integer>> A) {
        int N = A.size();
        int M = A.get(0).size();
        int DP[][] = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(DP[i], MIN);
        }
        // solve
        int h = solve(A, 0, 0, N, M, DP);
        if(h < 0) {
            return (h * -1) + 1;
        }
        // it will be always 1
        return 1; 
    }
}
