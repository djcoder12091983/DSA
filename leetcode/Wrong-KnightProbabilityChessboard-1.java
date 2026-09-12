// https://leetcode.com/problems/knight-probability-in-chessboard/
// TODO -- Again needs to think probability computation efficiently!

class Solution {

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

    int countmoves(int n, int k, int i, int j, Map<String, Integer> DP) {
        
        if(i < 0 || i >= n || j < 0 || j >= n) {
            // stopped but outside the board
            return 0;
        }
        
        if(k == 0) {
            // stopped but on board
            return 1;
        }

        String key = i + "-" + j + ":" + k;
        if(DP.containsKey(key)) {
            return DP.get(key);
        }

        // eight possible moves
        int ans = 0;
        for(int m = 0; m < 8; m++) {
            ans += countmoves(n, k - 1, i + moves[m][0], j + moves[m][1], DP);
        }

        DP.put(key, ans);

        return ans;
    }

	// what if total is odd
	// TODO how to compute probability efficiently!
    int count2(int t) {
        int c = 0;
        while(t > 0) {
            if(t % 2 == 0) {
                t = t / 2;
                c++;
            } else {
                break;
            }
        }

        return c;
    }

    public double knightProbability(int n, int k, int row, int column) {

        Map<String, Integer> DP = new HashMap<>();
        int total = countmoves(n, k, row, column, DP);

        System.out.println(total);

        if(total == 0) {
            // no valid move
            return 0;
        }

        // probability -- total moves / 2 power 3 * k (which is 1 / 8 power)
        // as total moves eaxctly 8
        int c = count2(total);
        return 1.0 / Math.pow(2, 3 * k - c);
    }
}