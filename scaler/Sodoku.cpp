// check if any cell unassigned
bool find_unassigned_locations(vector<vector<int>> &G, int &row, int &col, int N) {
    for(row = 0; row < N; row++) {
        for(col = 0; col < N; col++) {
            if(G[row][col] == 0) {
                return true;
            }
        }
    }
    return false;
}
// below three methods for checking row/column/box used with given number or not
bool used_row(vector<vector<int>> &G, int row, int x, int N) { 
    for(int i = 0; i < N; i++) {
        if(G[row][i] == x) {
            return true;
        }
    }
    return false;
}
  
bool used_col(vector<vector<int>> &G, int col, int x, int N) {
    for(int i = 0; i < N; i++) {
        if(G[i][col] == x) {
            return true;
        }
    }
    return false;
}
  
bool used_box(vector<vector<int>> &G, int start_row, int start_col, int x) {
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            if(G[i + start_row][j + start_col] == x) {
                return true;
            }
        }
    }
    return false;
} 

// check for move validity 
bool valid_move(vector<vector<int>> &G, int row, int col, int x, int N) {
    return !used_row(G, row, x, N) && !used_col(G, col, x, N)
            && !used_box(G, row - row % 3, col - col % 3, x)
            && G[row][col] == 0; 
}

bool try_to_solve(vector<vector<int>> &G, int N) {
    int row, col;
    if(!find_unassigned_locations(G, row, col, N)) {
        return true;
    }
    // consider digits 1 to 9  
    for (int d = 1; d <= N; d++) {
        if(valid_move(G, row, col, d, N)) {
            // make tentative assignment  
            G[row][col] = d;
            if (try_to_solve(G, N)) {  
                // solved
                return true;
            }
            // undo
            G[row][col] = 0;  
        }
    }
    return false; // backtracking
}

void Solution::solveSudoku(vector<vector<char>> &G) {
    int N = G.size();
    vector<vector<int>> g_n;
    // for easy computation convert it to numeric
    for(int i = 0; i < N; i++) {
        vector<int> row_n;
        for(int j = 0; j < N; j++) {
            if(G[i][j] == '.') {
                row_n.push_back(0);
            } else {
                row_n.push_back(G[i][j] - 48);
            }
        }
        g_n.push_back(row_n);
    }
    
    try_to_solve(g_n, N);
    
    // convert back to original
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            G[i][j] = g_n[i][j] + 48;
        }
    }
}
