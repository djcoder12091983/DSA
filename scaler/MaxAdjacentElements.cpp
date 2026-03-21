int Solution::adjacent(vector<vector<int>> &A) {
    // <gfg>/maximum-sum-such-that-no-two-elements-are-adjacent/
    int incl = max(A[0][0], A[1][0]);
    int excl = 0, excl_new; 
  
    int N = A[0].size();
    for(int i = 1; i < N; i++) {
        excl_new = max(excl, incl);
        incl = excl + max(A[0][i], A[1][i]);
        excl = excl_new;
    }
    return max(excl, incl);
}
