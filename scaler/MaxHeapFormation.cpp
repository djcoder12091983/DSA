#define MAXN 105
#define MOD 1000000007

typedef long long int ll;

// pre computed values
ll heaps[MAXN]; 
ll nck[MAXN][MAXN]; 
int log_2[MAXN];

// nCk
ll choose(int n, int k) {
    if(k > n) {
        return 0L;
    }
    if(n <= 1) {
        return 1L;
    }
    if(k == 0) {
        return 1L;
    }
    if(nck[n][k] != -1) {
        return nck[n][k];
    }
    ll c1 = choose(n - 1, k - 1);
    ll c2 = choose(n - 1, k);
    int answer = c1 + c2;
    if(answer >= MOD) {
        answer %= MOD;
    }
    nck[n][k] = answer;
    return answer;
}

// leaft heap build
int left(int n) {
    if(n == 1) {
        return 0;
    }
    int h = log_2[n];
    int numh = (1 << h);
    int last = n - ((1 << h) - 1); 
    if(last >= (numh / 2)) {
        return (1 << h) - 1;
    } else {
        return (1 << h) - 1 - ((numh / 2) - last);
    }
}

// heap construction
ll count(int n) {
    if(n <= 1) {
        return 1L;
    }
    if(heaps[n] != -1L) {
        return heaps[n];
    }
    int l = left(n);
    ll ans = choose(n - 1, l);
    ans *= count(l);
    if(ans >= MOD) {
        ans %= MOD;
    }
    ans *= count(n - 1 - l);
    if(ans >= MOD) {
        ans %= MOD;
    }
    heaps[n] = ans;
    return ans;
}

int Solution::solve(int N) {
    // note: <gfg>/number-ways-form-heap-n-distinct-integers/
    for(int i = 0; i <= N; i++) {
        heaps[i] = -1L;
    }
    for(int i = 0; i <= N; i++) {
        for(int j = 0; j <= N; j++) {
            nck[i][j] = -1L;
        }
    }
    int cur_log2 = -1;
    int cur_p2 = 1;
    // log2 computation
    for(int i = 1; i <= N; i++) {
        if(cur_p2 == i) {
            cur_log2++;
            cur_p2 *= 2;
        }
        log_2[i] = cur_log2;
    }
  
    return count(N) % MOD;
}
