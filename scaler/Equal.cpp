bool positional_index_comp(vector<pair<int, int>> &p1, vector<pair<int, int>> &p2) {
    return p1[0].first <= p2[0].first && p1[0].second < p2[0].second;
}

vector<int> Solution::equal(vector<int> &A) {
    unordered_map<int, vector<pair<int, int>>> indexing; // pair-sum vs positional index
    int N = A.size();
    for(int i = 0; i < N; i++) {
        for(int j = i + 1; j < N; j++) {
            // pair sum
            int s = A[i] + A[j];
            indexing[s].push_back({i, j});
        }
    }
    
    vector<vector<pair<int, int>>> positional_index;
    unordered_map<int, vector<pair<int, int>>>::iterator i;
    for(i = indexing.begin(); i != indexing.end(); i++) {
        vector<pair<int, int>> poisitions = i->second;
        positional_index.push_back(poisitions);
    }
    // sort positional index
    sort(positional_index.begin(), positional_index.end(), positional_index_comp);
    // now find suitable position
    for(vector<pair<int, int>> poisitions : positional_index) {
        N = poisitions.size();
        for(int j = 0; j < N; j++) {
            pair<int, int> p1 = poisitions[j];
            for(int k = j + 1; k < N; k++) {
                pair<int, int> p2 = poisitions[k];
                if(p1.first < p2.first && p1.second != p2.first && p1.second != p2.second) {
                    // suitable position found
                    return vector<int>{p1.first, p1.second, p2.first, p2.second};
                }
            }
        }
    }
}
