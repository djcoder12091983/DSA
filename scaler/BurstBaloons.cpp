int Solution::solve(vector<int> &A) {
    // <gfg>/burst-balloon-to-maximize-coins/
    
    int N = A.size();
    // bordering baloons
    int B[N + 2];
    B[0] = 1;
    B[N + 1] = 1;
    for(int i = 1; i <= N; i++) {
        B[i] = A[i - 1];
    }
    // DP table
    int DP[N + 2][N + 2];
    memset(DP, 0, sizeof(DP)); 
  
    for(int l = 1; l < N + 1; l++) {
        for(int i = 1; i < N - l + 2; i++) {
            int right = i + l - 1;
            // track the last baloon burst
            for(int j = i; j < right + 1; j++) {
                DP[i][right] = max(DP[i][right], DP[i][j - 1] + 
                        B[i - 1] * B[j] * B[right + 1] + DP[j + 1][right]);
            }
        }
    }
    return DP[1][N];
}