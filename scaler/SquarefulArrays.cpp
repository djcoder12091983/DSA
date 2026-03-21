typedef long long int LL;
int c; // this could be local

bool perfect_square(LL X) {
    // BS
    LL l = 1L, r = X;
    while(l <= r) {
        LL guess = (l + r) / 2;
        LL sqr = guess * guess;
        if(sqr == X) {
            return true;
        } else if(sqr < X) {
            // move right
            l = guess + 1;
        } else {
            // move left
            r = guess - 1;
        }
    }
    
    return false;
}

// this method ensures distinct permutation
bool swap_possible(vector<int> &A, int start, int idx) {
    for(int i = start; i < idx; i++) {
        if(A[i] == A[idx]) {
            // avoid redundant
            return false;
        }
    }
    return true; 
}

void permute(vector<int> &A, int idx, int N) {
    if(idx >= N) {
        LL a1 = A[N - 1], a2 = A[N - 2];
        if(perfect_square(a1 + a2)) {
            // permutation found
            c++;
        }
        return;
    }
    
    for(int i = idx; i < N; i++) {
        bool check = swap_possible(A, idx, i); 
        if(check) {
            // unique permutation
            swap(A[idx], A[i]);
            bool ok = true;
            if(idx >= 0 && idx - 1 >= 0) {
                LL a1 = A[idx], a2 = A[idx - 1];
                if(!perfect_square(a1 + a2)) {
                    // check for tentative solution exists
                    ok = false;
                }
            }
            if(ok) {
                // recusive call only if tentative solution exists
                permute(A, idx + 1, N);
            }
            swap(A[idx], A[i]);
        }
    }
}

int Solution::solve(vector<int> &A) {
   int N = A.size();
    
    c = 0;
    // solution
    permute(A, 0, N);
    
    return c;
}
