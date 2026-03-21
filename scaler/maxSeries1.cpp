vector<int> Solution::maxone(vector<int> &A, int B) {
    // using two pointers and sliding window
    int N = A.size();
    int max_1 = 0, c0 = 0;
    int i = 0, j = 0;
    int start, end;
    while(j < N) {
        if(A[j] == 0) {
            c0++;
            if(c0 > B) {
                c0--;
                int c1 = j - i;
                // one window covered
                if(c1 > max_1) {
                    max_1 = c1;
                    start = i;
                    end = j - 1;
                }
                
                // move i
                int k = i;
                for(;c0 == B; k++) {
                    if(A[k] == 0) {
                        c0--;
                        i = k + 1;
                    }
                }
                c0++;
            }
        }
        j++;
    }
    
    // last one
    int c1 = N - i;
    if(c1 > max_1) {
        start = i;
        end = N - 1;
    }
    
    // range
    vector<int> range;
    for(i = start; i <= end; i++) {
        range.push_back(i);
    }
    
    return range;
}
