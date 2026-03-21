#define MAX 10

// check for valid move
bool valid_move(int B[MAX][MAX], int row, int col, int N) {
    int i, j;
    // left check
    for(i = 0; i < col; i++) { 
        if(B[row][i]) {
            return false;
        }
    }
    // upper diagonal check
    for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
        if(B[i][j]) {
            return false;
        }
    }
    // lower diagonal check
    for (i = row, j = col; j >= 0 && i < N; i++, j--) {
        if(B[i][j]) {
            return false;
        }
    }
    return true; 
}

// try to get possible solutions
bool try_to_solve(int B[MAX][MAX], int col, vector<vector<string>> &result, int N) {
    if(col == N) {
        // solution found
        vector<string> possible_ans;
        for(int i = 0; i < N; i++) {
            string row;
            for(int j = 0; j < N; j++) {
                row.push_back(B[i][j] ? 'Q' : '.');
            }
            possible_ans.push_back(row);
        }
        
        result.push_back(possible_ans);
        return true;
    }
    
    // try placing one by one
    bool ok = false; 
    for(int i = 0; i < N; i++) {
        // try to place
        if(valid_move(B, i, col, N)) {
            B[i][col] = 1;
            ok = try_to_solve(B, col + 1, result, N) || ok;
            B[i][col] = 0; // undo
        }
    }
    return ok;
}

vector<vector<string> > Solution::solveNQueens(int size) {
    // result
    vector<vector<string>> result;
    int N = size; // size of chessboard
    
    int B[MAX][MAX]; 
    memset(B, 0, sizeof(B));
    
    try_to_solve(B, 0, result, N);
    
    return result;
}
