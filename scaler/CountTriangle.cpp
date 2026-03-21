#define MODULO 1000000007
typedef long long int LL;

int Solution::solve(vector<int> &A, vector<int> &B) {
    unordered_map<int, LL> x_f, y_f; // x and y axis point grouping
    int N = A.size();
    for(int i = 0; i < N; i++) {
        x_f[A[i]]++;
        y_f[B[i]]++;
    }
    
    // now try to construct triangle
    LL C = 0L;
    for(int i = 0; i < N; i++) {
        LL c1 = x_f[A[i]] - 1L, c2 = y_f[B[i]] - 1L;
        C += c1 * c2;
        if(C >= MODULO) {
            // modulo reduction
            C -= MODULO;
        }
    }
    
    if(C >= MODULO) {
        // modulo reduction
        C -= MODULO;
    }
    return C;
}
