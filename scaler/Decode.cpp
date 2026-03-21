#define MOD 1000000007
typedef long long int ll;

ll count(string &A, int idx, int N, ll DP[]) {
    if(idx == N) {
        // base case
        return 1L;
    }
    if(DP[idx] != -1L) {
        // precomputed result
        return DP[idx];
    }
    // take first
    ll c = 0;
    if(idx + 1 <= N) {
        if(idx + 1 == N || A[idx + 1] - 48 > 0) {
            // valid, go with it
            c += count(A, idx + 1, N, DP);
            if(c >= MOD) {
                // modulo reduction
                c %= MOD;
            }
        }
    }
    // take next two if possible
    if(idx + 2 <= N) {
        int s2 = 10 * (A[idx] - 48) + (A[idx + 1] - 48);
        if(s2 <= 26) {
            if(idx + 2 == N || A[idx + 2] - 48 > 0) {
                // valid, go with it
                c += count(A, idx + 2, N, DP);
                if(c >= MOD) {
                    // modulo reduction
                    c %= MOD;
                }
            }
        }
    }
    DP[idx] = c; // store computed result
    return c;
}

int Solution::numDecodings(string A) {
    int N = A.length();
    if(A[0] - 48 == 0) {
        // not possible
        return 0;
    }
    for(int i = 0; i < N - 1; i++) {
        int s2 = 10 * (A[i] - 48) + (A[i + 1] - 48);
        if(s2 > 26 && s2 % 10 == 0) {
            // not possible
            return 0;
        }
    }
    ll DP[N];
    memset(DP, -1L, sizeof(DP));
    return count(A, 0, N, DP);
}
