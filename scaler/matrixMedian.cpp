int Solution::findMedian(vector<vector<int> > &A) {
    // choose mid of min and max then find it's position
    // according to that position move mid pointer left/right
    int min_a = INT_MAX, max_a = INT_MIN;
    int R = A.size();
    int C = A[0].size();
    for(int i = 0; i < R; i++) {
        min_a = min(min_a, A[i][0]);
        max_a = max(max_a, A[i][C - 1]);
    }
    
    int P = (R * C + 1) / 2;
    while(min_a < max_a) {
        int mid = (min_a + max_a) / 2;
        int idx = 0;
        for(int i = 0; i < R; i++) {
            // count lower than mid for each row
            idx += upper_bound(A[i].begin(), A[i].end(), mid) - A[i].begin();
        }
        if(idx < P) {
            // take right
            min_a = mid + 1;
        } else {
            // take left
            max_a = mid;
        }
    }
    return min_a;
}
