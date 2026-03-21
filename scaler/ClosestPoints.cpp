#define MODULO 1000000007

typedef long long int LL;

// modulo power 2
LL p2(int y) {
    LL p = 1L;
    LL x = 2;
    while(y > 0) {
        if(y & 1) {
            p = (p * x) % MODULO;
        }
        y = y >> 1;
        x = (x * x) % MODULO;
    }
    return p;
}

int Solution::solve(vector<int> &A) {
    // greedy approach
    // the idea is [min, a1, a2 ... ak ...an, max]
    // one subsequence will be (max - min) * 2^(length of list)
    
    sort(A.begin(), A.end());
    
    int N = A.size();
    LL total_diff = 0L;
    int min_a = A[0];
    int idx = 0;
    // discard adjacent same elements
    for(int i = 1; i < N; i++) {
        if(A[i] != min_a) {
            // now calculate for current min and current max
            for(int j = idx; j < i; j++) {
                for(int k = i; k < N; k++) {
                    int max_a = A[k];
                    int l = k - j - 1;
                    total_diff += (max_a - min_a) * p2(l);
                    if(total_diff >= MODULO) {
                        // modulo reduction
                        total_diff %= MODULO;
                    }
                }
            }
            
            min_a = A[i];
            idx = i;
        }
    }
    
    return total_diff % MODULO;
}
