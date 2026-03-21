// lexographical order
bool comp(vector<int> &A, vector<int> &B) {
    int a = A.size(), b = B.size();
    int i = 0, j = 0;
    while(i < a && j < b) {
        if(A[i] < B[i]) {
            return true;
        } else if(A[i] > B[i]) {
            return false;
        } else {
            i++;
            j++;
        }
    }
    return a < b;
}

vector<vector<int> > Solution::subsetsWithDup(vector<int> &A) {
    // i always prefer to solve using bit mask ;)
    int bit_mask = 0;
    int N = A.size();
    int limit = 1 << N; // power set
    
    sort(A.begin(), A.end()); // sort it initially
    
    vector<vector<int>> power_set;
    // all possible subsets
    for(int i = 0; i < limit; i++) {
        vector<int> row_set;
        for(int j = 0; j < N; j++) {
            if(i & (1 << j)) {
                // include this element
                row_set.push_back(A[j]);
            }
        }
        if(find(power_set.begin(), power_set.end(), row_set) == power_set.end()) {
            // avoid duplicate subsets
            power_set.push_back(row_set);
        }
    }
    
    // lexographical order
    sort(power_set.begin(), power_set.end(), comp);
    
    return power_set;
}
