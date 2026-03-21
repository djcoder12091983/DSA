int total_zero;

// try to solve in DFS manner
int try_to_solve(vector<vector<int> > &A, int N, int M, int row, int col, int zero_c) {
    if(A[row][col] == 2) {
        // target reached
        return zero_c == total_zero + 1; // all zero covered
    }
    
    // mark as visited
    A[row][col] = 3;
    
    int next_options[][2] = {
        {row + 1, col},
        {row - 1, col},
        {row, col + 1},
        {row, col - 1}
    };
    
    int c = 0;
    for(int i = 0; i < 4; i++) {
        int x = next_options[i][0], y = next_options[i][1];
        if(x >= 0 && x < N && y >= 0 && y < M && (A[x][y] == 0 || A[x][y] == 2)) {
            // valid move
            c += try_to_solve(A, N, M, x, y, zero_c + 1);
        }
    }
    
    A[row][col] = 0; // undo
    
    return c;
}

int Solution::solve(vector<vector<int> > &A) {
    total_zero = 0;
    int N = A.size();
    int M = A[0].size();
    int start_i, start_j;
    // basic informations
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            if(A[i][j] == 0) {
                total_zero++;
            } else if(A[i][j] == 1) {
                start_i = i, start_j = j;
            }
        }
    }
    
    // try to solve DFS manner
    return try_to_solve(A, N, M, start_i, start_j, 0);
}
