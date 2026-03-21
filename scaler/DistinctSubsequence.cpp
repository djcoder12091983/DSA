int Solution::numDistinct(string A, string B) {
    int M = B.length(), N = A.length();
    if(M > N) {
        return 0;
    }
  
    int DP[M + 1][N + 1]; 
    for(int i = 1; i <= M; i++) {
        DP[i][0] = 0;
    }
    for(int j = 0; j <= N; j++) {
        DP[0][j] = 1;
    }
    // fill in bottom up manner 
    for(int i = 1; i <= M; i++) {
        for(int j = 1; j <= N; j++) {
            if(B[i - 1] != A[j - 1]) {
                DP[i][j] = DP[i][j - 1];
            } else {
                DP[i][j] = DP[i][j - 1] + DP[i - 1][j - 1];
            }
        }
    }
    return DP[M][N]; 
}