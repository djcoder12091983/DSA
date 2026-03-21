int Solution::solve(vector<int> &A, vector<int> &B, vector<int> &C, int D) {
    // modified version of unbounded binary knapsack
    int N = A.size();
    int DP[D + 1];
    memset(DP, 0, sizeof(DP)); // default fill
    // populate table top down manner
    int max_sweet = 0;
    for(int i = 1; i <= D; i++) {
        for(int j = 0; j < N; j++) {
            int c = C[j];
            int a = A[j];
            int b = B[j];
            int x = i - c;
            if(x >= 0) {
                // select the candy
                DP[i] = max(DP[i], DP[x] + a*b);
            }
        }
        max_sweet = max(max_sweet, DP[i]);
    }
    
    return max_sweet;
}
