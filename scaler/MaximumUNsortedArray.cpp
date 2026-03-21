vector<int> Solution::subUnsort(vector<int> &A) {
    vector<int> original = A; // save original array to compare
    // now sort to see changes
    sort(A.begin(), A.end());
    
    int N = A.size();
    int start_idx = -1, end_idx = -1;
    for(int i = 0; i < N; i++) {
        if(A[i] != original[i]) {
            // sequence break
            if(start_idx == - 1) {
                // set first time
                start_idx = i;
            }
            end_idx = i; // latest end index
        }
    }
    
    vector<int> subarray_idx;
    if(start_idx != -1) {
        subarray_idx.push_back(start_idx);
        subarray_idx.push_back(end_idx);
    } else {
        // no changes
        subarray_idx.push_back(-1);
    }
    
    return subarray_idx;
}
