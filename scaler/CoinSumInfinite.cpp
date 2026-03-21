#define MOD 1000007

int Solution::coinchange2(vector<int> &A, int B) {
    int N = A.size();
    int DP[B + 1];
    memset(DP, 0, sizeof(DP));
    // base case
    DP[0] = 1;
    // pick all coins one by one and update the table
    // after the index greater than or equal to the value of the
    // picked coin
    for(int i = 0; i < N; i++) {
        for(int j = A[i]; j <= B; j++) {
            DP[j] += DP[j - A[i]];
            if(DP[j] >= MOD) {
                DP[j] %= MOD;
            }
        }
    }
    return DP[B] % MOD;
}
