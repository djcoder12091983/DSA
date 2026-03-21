#define MOD 1000000007
typedef long long int ll;

int Solution::solve(int A, int B) {
    ll DP[B + 1][A + 1]; // DP i can be result counting j digits
    memset(DP, 0L, sizeof(DP));
    // base cases
    for(int i = 0; i <= A; i++) {
        DP[0][i] = 1L;
    }
    for(int i = 1; i <= B; i++) {
        DP[i][0] = 0L;
    }
    // fill the table (top down manner) and result is DP[B][A]
    for(int i = 1; i <= B; i++) {
        for(int j = 1; j <= A; j++) {
            // starts with 1-9 digits
            int k = i == B; // for B starts with 1 otherwise 0
            for(; k <= 9; k++) {
                int rem = i - k;
                if(rem >= 0) {
                    DP[i][j] += DP[rem][j - 1];
                    if(DP[i][j] >= MOD) {
                        // modulo reduction
                        DP[i][j] -= MOD;
                    }
                } else {
                    break;
                }
            }
        }
    }
    // test
    /*for(int i = 0; i <= B; i++) {
        for(int j = 1; j <= A; j++) {
            cout << DP[i][j] << " ";
        }
        cout << "\n";
    }*/
    
    return DP[B][A] % MOD;
}
