typedef long long int LL;

// whether distribution possible across people
bool possible(vector<int> A, int N, int B, LL pages) {
    int sc = 1;
    LL S = 0;
    for(int i = 0; i < N; i++) {
        if(A[i] > pages) {
            // distribution not possible
            return false;
        }
        // required students to distribute
        if(S + A[i] > pages) {
            sc++;
            S = A[i]; // reset sum
            if(sc > B) {
                // required students greater than available students
                return false;
            }
        } else {
            S += A[i];
        }
    }
    return true;
}

int Solution::books(vector<int> &A, int B) {
    // BS over [1 to sum of whole array]
    int N = A.size();
    if(B > N) {
        // straight way not possible
        return -1;
    }
    LL max_s = 0L;
    for(int i = 0; i < N; i++) {
        max_s += A[i];
    }
    // do BS over search spcae as mentioned earlier
    LL L = 1L, R = max_s;
    LL min_load = LONG_MAX;
    while(L <= R) {
        LL c_pages = (L + R) / 2;
        // find K for current value
        bool ok = possible(A, N, B, c_pages);
        if(ok) {
            // take left to get closed answer
            R = c_pages - 1;
            min_load = c_pages;
        } else {
            // need extra load
            L = c_pages + 1;
        }
    }
    
    return min_load;
}