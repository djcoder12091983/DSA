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

vector<vector<int> > Solution::combine(int A, int B) {
    // i always prefer to solve using bit mask ;)
    int bit_mask = 0;
    int N = A;
    vector<int> a_set;
    for(int i = 1; i <= A; i++) {
        a_set.push_back(i);
    }
    
    int limit = 1 << N; // power set
    vector<vector<int>> b_set;
    // all possible subsets
    for(int i = 0; i < limit; i++) {
        vector<int> row_set;
        for(int j = 0; j < N; j++) {
            if(i & (1 << j)) {
                // include this element
                row_set.push_back(a_set[j]);
            }
        }
        
        if(row_set.size() == B) {
            // specific subset
            b_set.push_back(row_set);
        }
    }
    
    // lexographical order
    sort(b_set.begin(), b_set.end(), comp);
    
    return b_set;
}
