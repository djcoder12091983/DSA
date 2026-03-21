int choose_and_try(int A, vector<vector<int>> &B, int C, int N, int M) {
    if(A == -1) {
        return 0;
    }
    // note: discard row/column which don't require
    // row wise check
    bool ok = true;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            int sum = 0;
            for(int k = j; k < M; k++) {
                sum += B[i][k];
                if(sum > C) {
                    // constraint violation
                    ok = false;
                    for(int x = j; x <= k; x++) {
                        B[i][x] *= -1;
                        ok |= choose_and_try(A - 1, B, C, N, M);
                        B[i][x] *= -1; // backtrack
                    }
                    return ok;
                }
            }
        }
    }
    
    // column wise
    for(int j = 0; j < M; j++) {
        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int k = i; k < N; k++) {
                sum += B[k][j];
                if(sum > C) {
                    // constraint violation
                    ok = false;
                    for(int x = i; x <= k; x++) {
                        B[x][j] *= -1;
                        ok |= choose_and_try(A - 1, B, C, N, M);
                        B[x][j] *= -1; // backtrack
                    }
                    return ok;
                }
            }
        }
    }
    return ok;
}

int Solution::solve(int A, vector<vector<int>> &B, int C) {
    int N = B.size(), M = B[0].size();
    return choose_and_try(A, B, C, N, M);
}
