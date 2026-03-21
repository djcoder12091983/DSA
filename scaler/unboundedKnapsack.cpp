int Solution::solve(int A, vector<int> &B, vector<int> &C) {
    // coin change problem with additional property (maximum property)
    // 1D DP required
    int DP[A + 1]; // tracks maximum profit @ DP[i] using unlimited weights
    int N = B.size();
    memset(DP, 0, sizeof(DP));
    for(int i = 1; i <= A; i++) {
        for(int j = 0; j < N; j++) {
            int req = i - C[j];
            if(req >= 0) {
                DP[i] = max(DP[i], B[j] + DP[req]);
            }
        }
    }
    
    return DP[A];
}