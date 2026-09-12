// https://leetcode.com/problems/knight-probability-in-chessboard/
// TODO -- Long time back solved but it's not accepted.
// TODO -- BUG FIX!

class Solution {

    double t1 = 0, t2 = 0;

    int moves[][] = {
        {2, 1},
        {2, -1},
        {-2, 1},
        {-2, -1},
        {1, 2},
        {1, -2},
        {-1, 2},
        {-1, -2}
    };

    void countmoves(int n, int k, int i, int j) {
        
        if(i < 0 || i >= n || j < 0 || j >= n) {
            // stopped but outside the board
            t2++;
            return;
        }
        
        if(k == 0) {
            // stopped but on board
            t1++;
            return;
        }

        // eight possible moves
        for(int m = 0; m < 8; m++) {
            countmoves(n, k - 1, i + moves[m][0], j + moves[m][1]);
        }

    }

    public double knightProbability(int n, int k, int row, int column) {
        countmoves(n, k, row, column);
        return t1/(t1 + t2);
    }
}