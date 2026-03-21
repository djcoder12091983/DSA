#define MODULO 1000000007

typedef long long int LL;

LL mod(LL a) {
    return (a + MODULO) % MODULO;
}

vector<int> Solution::solve(vector<vector<int> > &A, vector<int> &B, vector<int> &C, vector<int> &D, vector<int> &E) {
    // dynamic programming to store area
    int RN = A.size();
    int CN = A[0].size();
    LL DP[RN + 1][CN + 1];
    // base cases
    for(int i = 0; i <= CN; i++) {
        DP[0][i] = 0;
    }
    for(int i = 0; i <= RN; i++) {
        DP[i][0] = 0;
    }
    // pre compute area to answer query
    for(int i = 1; i <= RN; i++) {
        for(int j = 1; j <= CN; j++) {
            LL a = A[i - 1][j - 1];
            DP[i][j] = mod(a) + mod(DP[i - 1][j]) + mod(DP[i][j - 1]) + mod(-DP[i - 1][j - 1]);
            DP[i][j] %= MODULO;
        }
    }
    // answer query
    int QN = B.size();
    vector<int> qr;
    for(int i = 0; i < QN; i++) {
        int left = B[i], top = C[i];
        int right = D[i], bottom = E[i];
        LL A = mod(DP[right][bottom]) + mod(-DP[left - 1][bottom]) + mod(-DP[right][top - 1]) + mod(DP[left - 1][top - 1]);
        A %= MODULO;
        qr.push_back((int)A);
    }
    return qr;
}
