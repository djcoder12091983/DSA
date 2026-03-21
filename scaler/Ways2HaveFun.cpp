#define MOD 1000000007

typedef long long int ll;

// recursive solve using DP
unordered_map<int, ll> TS, TP; // S/P level track along with T
unordered_map<int, ll> S, P, T; // all level track

// recursively solve
ll count(char cur_act, char prev_act, int level, int limit) {
    if(level == limit) {
        // leaf (base case)
        return 1;
    }
    // reuse computed values
    if(prev_act == 'T') {
        if(cur_act == 'S') {
            if(TS.find(level) == TS.end()) {
                // recursive solution
                ll c = 0L;
                c += count('S', 'S', level + 1, limit) % MOD;
                c += count('P', 'S', level + 1, limit) % MOD;
                
                TS[level] = c;
            }
            return TS[level];
        } else if(cur_act == 'P') {
            if(TP.find(level) == TP.end()) {
                // recursive solution
                ll c = 0L;
                c += count('S', 'P', level + 1, limit) % MOD;
                
                TP[level] = c;
            }
            return TP[level];
        }
    } else {
        if(cur_act == 'S') {
            if(S.find(level) == S.end()) {
                // recursive solution
                ll c = 0L;
                c += count('S', 'S', level + 1, limit) % MOD;
                c += count('P', 'S', level + 1, limit) % MOD;
                c += count('T', 'S', level + 1, limit) % MOD;
                
                S[level] = c;
            }
            return S[level];
        } else if(cur_act == 'P') {
            if(P.find(level) == P.end()) {
                ll c = 0L;
                c += count('S', 'P', level + 1, limit) % MOD;
                c += count('T', 'P', level + 1, limit) % MOD;
                
                P[level] = c;
            }
            return P[level];
        } else {
            if(T.find(level) == T.end()) {
                // recursive solution
                ll c = 0L;
                c += count('S', 'T', level + 1, limit) % MOD;
                c += count('P', 'T', level + 1, limit) % MOD;
                
                T[level] = c;
            }
            return T[level];
        }
    }
}

int Solution::solve(int A) {
    // reset global variables
    TS.clear();
    TP.clear();
    S.clear();
    P.clear();
    T.clear();
    
    // recursive call
    ll cs = count('S', 'N', 1, A); // starts with S
    ll cp = count('P', 'N', 1, A); // starts with P
    ll ct = count('T', 'N', 1, A); // starts with T
    
    return (cs + cp + ct) % MOD;
}