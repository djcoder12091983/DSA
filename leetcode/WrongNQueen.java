// https://leetcode.com/problems/n-queens/
// TODO need to think in a simpler way

class Solution {

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

        // check diagonal
        int p1 = x, p2 = y;
        c = 0;
        // up diagonal
        while(p1 >= 0 && p2 >= 0) {
            if(B[p1][p2] == 'Q') {
                c++;
            }

            if(c == 2) {
                // not a valid state
                return false;
            }

            p1--;
            p2--;
        }

        // down diagonal
        p1 = x + 1;
        p2 = y + 1;
        while(p1 < n && p2  < n) {
            if(B[p1][p2] == 'Q') {
                c++;
            }

            if(c == 2) {
                // not a valid state
                return false;
            }

            p1++;
            p2++;
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
        solve(B, 0, ans);

        return ans;
    }

    boolean solve(char B[][], int idx, List<List<String>> ans) {

        int n = B.length;

        if(idx == n*n) {
            // done, convert B into answer
            List<String> s = new ArrayList<>(n);
            for(int i = 0; i < n; i++) {
                s.add(new String(B[i]));
            }
            ans.add(s);

            return true;
        }

        int x = idx / n, y = idx % n;
        if(B[x][y] == '.') {
            
            // put the character and see whether it's valid or not
            B[x][y] = 'Q';
            boolean flag = valid(B, x, y);

            // DEBUG
            /*
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    System.out.print(B[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("FLAG: " + flag);
            */

            if(flag) {
                boolean state = solve(B, idx + 1, ans);
                if(state) {
                    // valid solution
                    B[x][y] = '.'; // we will look for next solution 
                    return true;
                } else {
                    // restore back
                    B[x][y] = '.';
                    return solve(B, idx + 1, ans); // try out next
                }
            } else {
                B[x][y] = '.'; // restore back
                return solve(B, idx + 1, ans); // try out next
            }
        }

        return solve(B, idx + 1, ans); // if it's already a digit then it's a valid state
    }
}