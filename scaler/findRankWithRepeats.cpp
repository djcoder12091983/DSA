//ref: <interviewbit>/problems/sorted-permutation-rank-with-repeats/

#define MODULO 1000003
typedef long long int LL;

void init_facts(int N, vector<LL> &facts) {
    LL f = 1;
    facts.push_back(1);
    for (int i = 1; i < N; i++) {
        f = (f * i) % MODULO;
        facts.push_back(f);
    }
    return;
}

// modulo inverse
LL mod_inverse(LL A) {
    LL M = MODULO;
    LL M0 = M;
    LL y = 0, x = 1;
  
    while(A > 1) {
        LL q = A / M;
        LL t = M;
        M = A % M, A = t; 
        t = y;
        y = x - q * y;
        x = t;
    }
    if(x < 0) {
       x += M0;
    } 
  
    return x; 
}

int Solution::findRank(string A) {
    int c_f[256]; 
    memset(c_f, 0, sizeof(c_f));
    int N = A.length();
    for(int i = 0; i < N; i++){
        c_f[A[i]]++;
    }
    vector<LL> facts;
    init_facts(N + 1, facts);
    
    LL rank = 1;
    for(int i = 0; i < N; i++) {
        LL less = 0;
        int req = N - i - 1;
        for(int ch = 0; ch < A[i]; ch++) {
            if(c_f[ch] == 0){
                continue;
            }
            c_f[ch]--;
            LL num_p = facts[req];
            for(int c = 0; c < 128; c++) {
                if (c_f[c] <= 1){
                    continue;
                }
                num_p = (num_p * mod_inverse(facts[c_f[c]])) % MODULO;
            }
            c_f[ch]++;
            less = (less + num_p) % MODULO;
        }
        
        rank = (rank + less) % MODULO;
        c_f[A[i]]--;
    }
    return (int)rank;
}
