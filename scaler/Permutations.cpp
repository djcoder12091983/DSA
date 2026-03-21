void solve(vector<vector<int>> &ans, vector<int> &A, int idx, int N) {
    if(idx >= N) {
        vector<int> ans_row;
        for(int i = 0; i < N; i++) {
            ans_row.push_back(A[i]);
        }
        ans.push_back(ans_row);
        return;
    }
    
    for(int i = idx; i < N; i++) {
        swap(A[idx], A[i]);
        // recusive call
        solve(ans, A, idx + 1, N);
        swap(A[idx], A[i]);
    }
}

vector<vector<int> > Solution::permute(vector<int> &A) {
    vector<vector<int>> ans;
    int N = A.size();
    
    // solution
    solve(ans, A, 0, N);
    
    return ans;
}
