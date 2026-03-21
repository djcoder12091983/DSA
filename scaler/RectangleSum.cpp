// maximize subarray sum
int maximize_sum(int X[], int N) {
    int max_sum = 0;
    int prev = 0;
    for(int i = 0; i < N; i++) {
        int t = prev + X[i];
        if(t > 0) {
            max_sum = max(t, max_sum);
            prev = t;
        } else {
            prev = 0;
        }
    }
    /*if(max_sum == 0) {
        // still zero then all are negative
        max_sum = *max_element(X, X + N);
    }*/
    
    return max_sum;
}

int Solution::solve(vector<vector<int>> &A) {
    int N = A.size(), M = A[0].size();
    int max_area = INT_MIN;
    int c_idx = 0;
    // note: TC O(M*N*M)
    int area[N];
    while(c_idx < M) {
        memset(area, 0, sizeof(area)); // reset
        for(int i = 0; i < N; i++) {
            area[i] = A[i][c_idx];
        }
        max_area = max(max_area, maximize_sum(area, N)); // maximum area so far
        for(int j = c_idx + 1; j < M; j++) {
            for(int i = 0; i < N; i++) {
                area[i] += A[i][j];
            }
            max_area = max(max_area, maximize_sum(area, N)); // maximum area so far
        }
        c_idx++;
    }
    
    return max_area;
}