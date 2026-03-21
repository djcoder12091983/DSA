typedef long long int ll;
ll MOD = 10000003;

int Solution::seats(string A) {
    // for each group need to check how many moves required from left
    // as well as from right
    int N = A.length();
    ll left[N], right[N];
    // memset might not be required
    memset(left, 0L, sizeof(left));
    memset(right, 0L, sizeof(right));
    
    // left to right scan
    int c = 1;
    ll moves = 0L, groups = 0L;
    for(int i = 1; i < N; i++) {
        if(A[i] != A[i - 1]) {
            // different with previous
            if(A[i - 1] == '.') {
                moves += groups * c;
                left[i] = moves;
            } else {
                groups += c;
            }
            
            // reset
            c = 1;
        } else {
            c++;
        }
    }
    // right to left
    moves = 0L, groups = 0L, c = 1;
    for(int i = N - 2; i >= 0; i--) {
        if(A[i] != A[i + 1]) {
            // different with previous
            if(A[i + 1] == '.') {
                moves += groups * c;
                right[i] = moves;
            } else {
                groups += c;
            }
            
            // reset
            c = 1;
        } else {
            c++;
        }
    }
    // now check each group, take minimum moves
    int start = -1, end = -1;
    ll min_moves = LONG_MAX;
    for(int i = 0; i < N; i++) {
        if(A[i] == 'x') {
            // start
            if(start == -1) {
                // once set no need to set
                start = i;
            }
        } else {
            // end
            end = i - 1;
            if(start != -1) {
                // one group available
                ll total_moves = left[start] + right[end];
                min_moves = min(total_moves, min_moves);
            }
            
            // reset
            start = end = -1;
        }
    }
    if(A[N - 1] == 'x') {
        end = N - 1;
        if(start != -1) {
            // one group available
            ll total_moves = left[start] + right[end];
            min_moves = min(total_moves, min_moves);
        }
    }
    if(min_moves == LONG_MAX) {
        // no people are there
        return 0;
    }
    
    return min_moves % MOD;
}
