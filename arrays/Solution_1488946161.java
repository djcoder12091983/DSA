class Solution {
    public boolean isValidSudoku(char[][] board) {
       for (int i=0; i<9; i++){
            Set<Character> set = new HashSet<>();
            for (int j=0; j<9; j++){
                char c = board[i][j];
                if (c>='1' && c<='9' && !set.add(c)){
                    return false;
                }
            }
        }
        for (int i=0; i<9; i++){
            Set<Character> set = new HashSet<>();
            for (int j=0; j<9; j++){
                char c = board[j][i];
                if (c>='1' && c<='9' && !set.add(c)){
                    return false;
                }
            }
        }

        
        for (int i=0; i<9; i += 3){
            for (int j=0; j<9; j += 3){
                Set<Character> set = new HashSet<>();
                for (int x = i; x<i+3; x++){
                    for (int y=j; y<j+3; y++){
                        char c = board[x][y];
                        if (c>='1' && c<='9' && !set.add(c)){
                            return false;
                        }
                    }
                }
            }
        }
        return true; 
    }
}