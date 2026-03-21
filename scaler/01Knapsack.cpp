int Solution::solve(vector<int> &A, vector<int> &B, int C) {
    // standard binary knapsack using 2D-DP
    int N = A.size();
    // selecting subset of size i elements and j weight maximum profit
    int DP[N + 1][C + 1];
    memset(DP, 0, sizeof(DP));
    // fill table top down manner
    for(int i = 1; i <= N; i++) {
        int v = A[i - 1];
        int w = B[i - 1];
        for(int j = 1; j <= C; j++) {
            // two options: choose current element or not
            int req = j - w;
            DP[i][j] = max(DP[i - 1][j], req >= 0 ? (v + DP[i - 1][req]) : 0);
        }
    }
    return DP[N][C]; // computed maximum value
}
