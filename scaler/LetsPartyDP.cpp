#define MOD 10003

typedef long long int ll;

int Solution::solve(int A) {
    // iterative DP approach
    ll DP[A + 1];
    DP[1] = 1L;
    DP[2] = 2L;
    for(int i = 3; i <= A; i++) { 
        DP[i] = DP[i - 1] + (i - 1) * DP[i - 2];
        if(DP[i] >= MOD) {
            // modulo reduction
            DP[i] %= MOD;
        }
    } 
    return DP[A] % MOD;
}
