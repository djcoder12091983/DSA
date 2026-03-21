class Solution {
    public String convert(String S, int R) {

        if(R == 1) {
            return S; // edge case
        }

        // output matrix
        int N = S.length();
        // number of columns can be N/R + 1, need to think it detail
        char[][] mat = new char[R][N];
        int rc[] = new int[R];
        for(int i = 0; i < R; i++) {
            rc[i] = 0;
        }

        int row = 0;
        boolean down = true;
        int i = 0;
        while(i < N) {
            mat[row][rc[row]] = S.charAt(i);
            rc[row]++; // row column index
            
            // move as per direction
            if(down == true) {
                row++;
            } else {
                row--;
            }

            // handle boundary and change direction
            if(down == true && row == R) {
                // move up
                row = R - 2;
                down = false;
            } else if(down == false && row == -1) {
                row = 1;
                down = true;
            }

            i++;
        }

        // form the string
        i = 0;
        StringBuilder ans = new StringBuilder();
        while(i < R) {
            System.out.println(i + " => " + new String(mat[i], 0, rc[i]));
            ans.append(mat[i], 0, rc[i]);
            i++;
        }

        return ans.toString();
    }
}