void count(vector<int> &A, int idx, int N, int B, vector<vector<int>> &combinations, vector<int> path) {
    // DFS fashion
    unordered_set<int> unique;
    for(int i = idx; i < N; i++) {
        if(unique.find(A[i]) != unique.end()) {
            // ensure unique combination
            continue;
        }
        unique.insert(A[i]);
        
        if(A[i] == B) {
            // result found
            vector<int> valid_path;
            int l = path.size();
            for(int j = 0; j < l; j++) {
                valid_path.push_back(path[j]);
            }
            valid_path.push_back(A[i]);
            combinations.push_back(valid_path);
        } else if(A[i] < B) {
            // look for more depth
            vector<int> next_path;
            int l = path.size();
            for(int j = 0; j < l; j++) {
                next_path.push_back(path[j]);
            }
            next_path.push_back(A[i]);
        
            // recursion call
            count(A, i + 1, N, B - A[i], combinations, next_path);
        }
    }
}

vector<vector<int>> Solution::combinationSum(vector<int> &A, int B) {
    // sort the numbers to reduce state spaces
    // and answers will be in sorted fashion
    sort(A.begin(), A.end());
    
    vector<vector<int>> combinations;
    // solve recursively
    count(A, 0, A.size(), B, combinations, vector<int>{});
    
    return combinations;    
}
