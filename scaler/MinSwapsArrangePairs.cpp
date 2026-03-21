// return valid pair
int valid_pair(int x, vector<vector<int>> &P) {
    for(vector<int> pair_detail : P) {
        if(pair_detail[0] == x) {
            return pair_detail[1];
        }
        if(pair_detail[1] == x) {
            return pair_detail[0];
        }
    }
    return -1;
}

// find position of given x
int find(int x, vector<int> &A) {
    int N = A.size();
    for(int i = 0; i < N; i++) {
        if(x == A[i]) {
            return i;
        }
    }
    return -1;
}

int Solution::solve(int N, vector<int> &A, vector<vector<int>> &P) {
    // try to solve in linear fashion
    int sc = 0;
    for(int i = 0; i < N; i++) {
        int p1 = A[2 * i];
        int p2 = valid_pair(p1, P);
        int odd_idx = 2 * i + 1;
        if(A[odd_idx] != p2) {
            // swap required
            sc++;
            int idx = find(p2, A);
            swap(A[odd_idx], A[idx]);
        }
    }
    return sc;
}
