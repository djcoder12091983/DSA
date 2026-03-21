public class Solution {
    
    // recursively solve
    int solve(ArrayList<ArrayList<Integer>> A, int l, int r, int N, int M, int DP[][]) {
        if(l == N - 1 && r == M - 1) {
            return 1;
        }
        if(DP[l][r] != -1) {
            // already computed so reuse it
            return DP[l][r];
        }
        // recursively solve
        int c = 0;
        if(l + 1 < N && A.get(l + 1).get(r) != 1) {
            // valid move
            c += solve(A, l + 1, r, N, M, DP);
        }
        if(r + 1 < M && A.get(l).get(r + 1) != 1) {
            // valid move
            c += solve(A, l, r + 1, N, M, DP);
        }
        DP[l][r] = c; // save result
        return c;
    }
    
    public int uniquePathsWithObstacles(ArrayList<ArrayList<Integer>> A) {
        int N = A.size();
        int M = A.get(0).size();
        if(A.get(0).get(0) == 1 || A.get(N - 1).get(M - 1) == 1) {
            // not possible
            return 0;
        }
        int DP[][] = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(DP[i], -1);
        }
        
        return solve(A, 0, 0, N, M, DP);
    }
}
