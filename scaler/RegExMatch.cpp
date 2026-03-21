int Solution::isMatch(const string A, const string B) {
    int N = A.length(), M = B.length();
    bool DP[N + 1][M + 1];
    memset(DP, false, sizeof(DP));
    DP[0][0] = true;
    // Only '*' can match with empty string 
    for(int i = 1; i <= M; i++) {
        if(B[i - 1] == '*') {
            DP[0][i] = DP[0][i - 1];
        }
    }
    // fill the table
    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= M; j++) {
            if(B[j - 1] == '*') {
                DP[i][j] = DP[i][j - 1] || DP[i - 1][j];
            } else if(A[i - 1] == B[j - 1] || B[j - 1] == '?') {
                DP[i][j] = DP[i - 1][j - 1];
            } else {
                DP[i][j] = false;
            }
        }
    }
    return DP[N][M];
}
