// https://leetcode.com/problems/sudoku-solver/
// TODO need to think of reducing execution time

class Solution {

    boolean valid(char B[][], int x, int y) {
        // we will check if a cell if filled with some value then need to check
        // whether it's a valid move or what

        // check col
        boolean v[] = new boolean[10];
        Arrays.fill(v, false);
        for(int i = 0; i < 9; i++) {
            char ch = B[i][y];
            if(ch != '.') {
                if(v[ch - '0'] == true) {
                    // already filled
                    return false;
                }

                v[ch - '0'] = true; // visited
            }
        }

        // check row
        Arrays.fill(v, false);
        for(int i = 0; i < 9; i++) {
            char ch = B[x][i];
            if(ch != '.') {
                if(v[ch - '0'] == true) {
                    // already filled
                    return false;
                }

                v[ch - '0'] = true; // visited
            }
        }

        // check box
        int bx = x / 3 * 3, by = y / 3 * 3;
        Arrays.fill(v, false);
        for(int i = bx; i < bx + 3; i++) {
            for(int j = by; j < by + 3; j++) {
                char ch = B[i][j];
                if(ch != '.') {
                    if(v[ch - '0'] == true) {
                        // already filled
                        return false;
                    }

                    v[ch - '0'] = true; // visited
                }
            }
        }

        // all validation passed
        return true;
    }

    public void solveSudoku(char[][] B) {
        solve(B, 0);
    }

    boolean solve(char B[][], int idx) {
        if(idx == 9*9) {
            // done
            return true;
        }

        int x = idx / 9, y = idx % 9;
        if(B[x][y] == '.') {
            // put all the options from 1 to 9
            for(char ch = '1'; ch <= '9'; ch++) {
                
                // put the character and see whether it's valid or not
                char old = B[x][y]; 
                B[x][y] = ch;
                if(valid(B, x, y)) {
                    boolean state = solve(B, idx + 1);
                    if(state) {
                        // valid solution
                        return true;
                    } else {
                        // restore back
                        B[x][y] = old;
                    }
                } else {
                    B[x][y] = old; // restore back
                }   
            }

            return false; // not found a valid solution
        }

        return solve(B, idx + 1); // if it's already a digit then it's a valid state
    }
}