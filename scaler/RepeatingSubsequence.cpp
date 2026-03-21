int Solution::anytwo(string A) {
    int N = A.size();
    int DP[N + 1][N + 1];
    for(int i = 0; i <= N; i++) {
        DP[i][0] = 0;
        DP[0][i] = 0;
    }
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            if(A[i] == A[j] && i != j) {
                // note: this condition i != j is trick
                // how it's deviated from classic LCS problem
                DP[i + 1][j + 1] = 1 + DP[i][j];
            } else {
                DP[i + 1][j + 1] = max(DP[i][j + 1], DP[i + 1][j]);
            }
        }
    }
    return DP[N][N] >= 2;
}
