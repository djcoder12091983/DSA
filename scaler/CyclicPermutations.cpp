// preprocessing with Z
void compute_z(string &S, int Z[]) {
    int N = S.length();
    int L, R, k;
    // [L,R] make a window which matches with prefix of S 
    L = R = 0;
    for(int i = 1; i < N; ++i) {
        // Z[i] using naive way. 
        if(i > R) {
            L = R = i;
            while(R < N && S[R-L] == S[R]) {
                R++;
            }
            Z[i] = R-L;
            R--;
        } else {
            // matches in [L,R] interval. 
            k = i-L; 
            if(Z[k] < R-i+1) {
                Z[i] = Z[k];
            } else {
                // start from R and check manually 
                L = i; 
                while(R < N && S[R-L] == S[R]) {
                    R++;
                }
                Z[i] = R-L;
                R--;
            }
        }
    }
}

int Solution::solve(string A, string B) {
    // ref: <geeksforgeeks>/z-algorithm-linear-time-pattern-searching-algorithm/
    
    // concatenate B with itself
    // now B contains all the cyclic permutations of old B
    B = B + B;
    int c = 0; 
    string S = A + '/' + B.substr(0, B.length() - 1); 
    int N = S.length();
    // process Z array
    int Z[N];
    Z[0] = 0;
    compute_z(S, Z); 
  
    int l = A.length();
    // now count
    for(int i = 0; i < N; i++) {
        if(Z[i] == l) {
            c++;
        }
    }
    return c;
}
