typedef long long int ll;
#define MOD 1000000007

int Solution::chordCnt(int X) {
    // <GFG>/count-ways-divide-circle-using-n-non-intersecting-chords/
    int N = 2 * X;
    // dp array containing the sum 
    ll DP[N + 1] = {0L};
    DP[0] = 1L;
    DP[2] = 1L;
    for(int i = 4; i <= N; i += 2) {
        for(int j = 0; j < i-1; j += 2) {
            ll t = DP[j] * DP[i - 2 - j];
            if(t >= MOD) {
                t %= MOD;
            }
            DP[i] += t;
            if(DP[i] >= MOD) {
                DP[i] %= MOD;
            }
        }
    }
    // result 
    return DP[N] % MOD;
}
