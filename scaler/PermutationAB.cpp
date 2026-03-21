int Solution::solve(string A, string B) {
    // similar idea of "minimum window substring"
    // but here exact match b2n substring and permuation of A
    int a_l = A.length();
    int a_f[26];
    memset(a_f, 0, sizeof(a_f));
    for(int i = 0; i < a_l; i++) {
        a_f[A[i] - 97]++;
    }
    
    // now try to find how permuation occurs in B
    int N = B.length();
    int b_f[26];
    memset(b_f, 0, sizeof(b_f));
    int c = 0, start = -1;
    for(int i = 0; i < N; i++) {
        int b = B[i] - 97;
        if(a_f[b] == 0) {
            // reset
            memset(b_f, 0, sizeof(b_f));
            start = -1;
        } else {
            if(start == -1) {
                // assign start
                start = i;
            }
            b_f[b]++;
            if(b_f[b] > a_f[b]) {
                // try to remove from left
                if(B[start] == b + 97) {
                    for(int j = start; j <= i; j++) {
                        b = B[j] - 97;
                        if(b_f[b] <= a_f[b]) {
                            // new start
                            start = j;
                            break;
                        } else {
                            // remove unwanted
                            b_f[b]--;
                        }
                    }
                }
            }
            
            // check whether permuation exists 
            bool ok = true;
            for(int j = 0; j < 26; j++) {
                if(a_f[j] != b_f[j]) {
                    ok = false;
                    break;
                }
            }
            c += ok;
        }
    }
    
    return c;
}
