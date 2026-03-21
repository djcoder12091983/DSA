public class Solution {
    
    // plaindrome check
    boolean check(String A, int l, int r) {
        while(l <= r) {
            char x = A.charAt(l);
            char y = A.charAt(r);
            if(x != y) {
                // not possible
                return false;
            }
            l++;
            r--;
        }
        return true; // palindrome
    }
    
    // recursively solve
    int solve(String A, int l, int r, int DP[][]) {
        if(l == r) {
            // not cut is required
            return 0;
        }
        if(DP[l][r] != -1) {
            // already computed so reuse it
            return DP[l][r];
        }
        if(check(A, l, r)) {
            // full palindorme, no split required
            DP[l][r] = 0;
            return DP[l][r];
        }
        // now recursively solve it
        int min = Integer.MAX_VALUE;
        for(int i = l; i < r; i++) {
            if(check(A, l, i)) {
                min = Math.min(min, 1 + solve(A, i + 1, r, DP));
            }
        }
        // store minimum split for this range
        DP[l][r] = min;
        return DP[l][r];
    }
    
    public int minCut(String A) {
        int N = A.length();
        int DP[][] = new int[N][N];
        for(int i = 0; i < N; i++) {
            Arrays.fill(DP[i], -1);
        }
        // solve
        return solve(A, 0, N - 1, DP);
    }
}
