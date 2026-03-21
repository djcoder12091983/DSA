// finds smallest index
int b_search(vector<int> &A, int B) {
    int l = 0, r = A.size() - 1;
    int idx = -1;
    while(l <= r) {
        int mid = (l + r) / 2;
        if(A[mid] >= B) {
            // take left
            if(A[mid] == B) {
                idx = mid;
            }
            r = mid - 1;
        } else {
            // take right
            l = mid + 1;
        }
    }
    return idx;
}

int Solution::solve(vector<vector<int> > &A, int B) {
    // greedy approach to find smallest i and j
    int N = A.size();
    for(int i = 0; i < N; i++) {
        int idx = b_search(A[i], B);
        if(idx != -1) {
            return (i + 1) * 1009 + (idx + 1); 
        }
    }
    return -1;
}
