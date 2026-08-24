// https://leetcode.com/problems/n-queens/
// TODO need to reduce execution time

class Solution {

    // diagonal check
    boolean diagonal(char B[][], int x, int y, int p1, int p2) {
        // p1 and p2 decide movements

        int n = B.length;
        while(x >= 0 && x < n && y >= 0 && y < n) {
            if(B[x][y] == 'Q') {
                return false;
            }

            x += p1;
            y += p2;
        }

        return true;
    }

    // validate the current state
    boolean valid(char B[][], int x, int y) {

        int n = B.length;
        // check row
        int c = 0;
        for(int i = 0; i < n; i++) {
            if(B[x][i] == 'Q') {
                c++;
            }

            if(c == 2) {
                // not a valid state
                return false;
            }
        }

        // check column
        c = 0;
        for(int i = 0; i < n; i++) {
            if(B[i][y] == 'Q') {
                c++;
            }

            if(c == 2) {
                // not a valid state
                return false;
            }
        }

        // check 4 diagonal
        int moves[][] = new int[][]{
            {+1, +1},
            {+1, -1},
            {-1, +1},
            {-1, -1}
        };
        for(int move[] : moves) {
            if(!diagonal(B, x + move[0], y + move[1], move[0], move[1])) {
                // not a valid state
                return false;
            }
        }

        // valid state, no violation
        return true;
    }

    public List<List<String>> solveNQueens(int n) {
        // create a board with empty
        char B[][] = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                B[i][j] = '.';
            }
        }

        List<List<String>> ans = new ArrayList<>();
        solve(B, 0, 0, ans);

        return ans;
    }

    void solve(char B[][], int idx, int q, List<List<String>> ans) {

        int n = B.length;

        if(idx == n*n) {
            // done, convert B into answer
            if(q == n) {
                // n queens successfully placed
                List<String> s = new ArrayList<>(n);
                for(int i = 0; i < n; i++) {
                    s.add(new String(B[i]));
                }
                ans.add(s);
            }
            
            return;
        }

        int x = idx / n, y = idx % n;
        B[x][y] = 'Q';
        // put Q
        if(valid(B, x, y)) {
            solve(B, idx + 1, q + 1, ans);
        }

        B[x][y] = '.';

        // not put Q
        solve(B, idx + 1, q, ans);
    }
}