typedef long long int LL;
const LL MODULO = 1000003;

LL fact(LL N) {
    LL f = 1;
    for(LL i = 1; i <= N; i++) {
        f *= i;
        if(f >= MODULO) {
            f %= MODULO;
        }
    }
    return f % MODULO;
}

int find_smaller_in_right(string A, int low, int high) {
    int cr = 0;
    for(int i = low + 1; i <= high; i++) {
        if(A[i] < A[low]) {
            cr++;
        }
    }
    return cr;
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
    // ref: <www.geeksforgeeks>/lexicographic-rank-of-a-string/
    int N = A.length();
    LL f = fact(N);
    LL rank = 1;
    int cr;
    for(int i = 0; i < N; i++) { 
        f *= mod_inverse(N - i);
        if(f >= MODULO) {
            f %= MODULO;
        }
        cr = find_smaller_in_right(A, i, N - 1);
        rank += (cr * f) % MODULO;
        if(rank >= MODULO) {
            rank %= MODULO;
        }
    }
  
    return (int)(rank % MODULO);
}