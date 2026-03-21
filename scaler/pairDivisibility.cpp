#define MODULO 1000000007

typedef long long int LL;

int Solution::solve(vector<int> &A, int B) {
    // rule: (A + B) % C = 0 iff A % C + B % C = C (distribution)
    LL remainder[B];
    memset(remainder, 0, sizeof(remainder));
    for(int a : A) {
        int rem = a % B;
        remainder[rem]++;
    }
    
    LL c = 0;
    for(int i = 0; i < B; i++) {
        LL c1 = remainder[i];
        if(c1) {
            if(i == 0) {
                // self pairing
                c += c1 * (c1 - 1) / 2L;
            } else {
                int req = B - i;
                if(req == i) {
                    // self pairing
                    c += c1 * (c1 - 1) / 2L;
                } else if(i < req) {
                    // avoid duplicate
                    LL c2 = remainder[req];
                    if(c2) {
                        // cross pairing
                        c += c1 * c2;
                    }
                }
            }
        }
        
        if(c >= MODULO) {
            // modulo reduction
            c %= MODULO;
        }
    }
    
    return c % MODULO;
}
