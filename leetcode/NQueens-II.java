// https://leetcode.com/problems/n-queens-ii/
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

    public int totalNQueens(int n) {
        // create a board with empty
        char B[][] = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                B[i][j] = '.';
            }
        }

        return solve(B, 0, 0);
    }

    int solve(char B[][], int idx, int q) {

        int n = B.length;

        if(idx == n*n) {
            // done, convert B into answer
            if(q == n) {
                return 1;
            } else {
                return 0;
            }
        }

        int c = 0;

        int x = idx / n, y = idx % n;
        B[x][y] = 'Q';
        // put Q
        if(valid(B, x, y)) {
            c += solve(B, idx + 1, q + 1);
        }

        B[x][y] = '.';

        // not put Q
        c += solve(B, idx + 1, q);

        return c;
    }
}