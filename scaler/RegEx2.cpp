int Solution::isMatch(const string A, const string B) {
    int N = A.length(), M = B.length();
    bool DP[N + 1][M + 1];
    memset(DP, false, sizeof(DP));
    DP[0][0] = true;
    
    // base cases
    for(int i = 1; i <= M; i++) {
        if(B[i - 1] == '*') {
            // * matches empty string also
            DP[0][i] = DP[0][i - 2];
        }
    }
    
    // solve using DP
    for(int i = 1; i <= N; i++) {
        char a = A[i - 1];
        for(int j = 1; j <= M; j++) {
            char b = B[j - 1];
            if(b == '.' || a == b) {
                DP[i][j] = DP[i - 1][j - 1];
            } else if(b == '*') {
                // * case
                if(B[j - 2] == a || B[j - 2] == '.') {
                    DP[i][j] = DP[i][j - 2] || DP[i - 1][j];
                } else {
                    DP[i][j] = DP[i][j - 2];
                }
            } else {
                DP[i][j] = false;
            }
        }
    }
    
    // test
    /*for(int i = 0; i <= N; i++) {
        for(int j = 0; j <= M; j++) {
            cout << DP[i][j] << " ";
        }
        cout << "\n";
    }*/
    
    return DP[N][M];
}
