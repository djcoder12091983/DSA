// find closed X
int bs(vector<int> &A, int X) {
    int l = 0, r = A.size() - 1;
    int X1 = A[0];
    int min_d = INT_MAX;
    while(l <= r) {
        int mid = (l + r) / 2;
        if(A[mid] <= X) {
            // take right
            l = mid + 1;
        } else {
            // take left
            r = mid - 1;
        }
        // set closed element
        int d = abs(X - A[mid]);
        if(d < min_d) {
            min_d = d;
            X1 = A[mid];
        }
    }
    return X1;
}

vector<int> Solution::solve(vector<int> &A, vector<int> &B, int C) {
    // solbve using BS
    // TODO need to solv using two pointers
    int N1 = A.size();
    int N2 = B.size();
    vector<int> pair_ab(2);
    int min_d = INT_MAX;
    for(int i = 0; i < N1; i++) {
        // for each A find closed to C in B
        int req = abs(A[i] - C);
        int b = bs(B, req);
        int d = abs(A[i] + b - C);
        if(d < min_d) {
            // new minimum difference
            min_d = d;
            // save pair
            pair_ab[0] = A[i];
            pair_ab[1] = b;
        }
    }
    return pair_ab;
}
