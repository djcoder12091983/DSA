public class Solution {
    // note: matrix multiplication is associative but not commutative
    
    // recursively solve
    int minimize(ArrayList<Integer> M, int l, int r, int DP[][]) {
        if(r - l == 1) {
            // one matrix
            return 0;
        } else if(DP[l][r] != -1) {
            // already precomputed
            return DP[l][r];
        }
        // possible situations
        int c = (M.get(l) * M.get(l + 1) * M.get(r)) + minimize(M, l + 1, r, DP);
        for(int i = l + 1; i < r; i++) {
            int t = (M.get(l) * M.get(i) * M.get(r)) + minimize(M, l, i, DP) + minimize(M, i, r, DP);
            c = Math.min(c, t);
        }
        c = Math.min(c, minimize(M, l, r - 1, DP) + (M.get(l) * M.get(r - 1) * M.get(r)));
        
        DP[l][r] = c; // store result
        return c;
    }
    
    public int solve(ArrayList<Integer> M) {
        int N = M.size();
        int DP[][] = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                DP[i][j] = -1;
            }
        }
        // recursively solve
        return minimize(M, 0, N - 1, DP);
    }
}
