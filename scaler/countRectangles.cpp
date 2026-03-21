int Solution::solve(vector<int> &A, vector<int> &B) {
    // group points by x-corordinate
    map<int, unordered_set<int>> x_group;
    int N = A.size();
    for(int i = 0; i < N; i++) {
        x_group[A[i]].insert(B[i]);
    }
    
    N = x_group.size();
    vector<unordered_set<int>> sorted_x_group;
    map<int, unordered_set<int>>::iterator x_group_i;
    for(x_group_i = x_group.begin(); x_group_i != x_group.end(); x_group_i++) {
        unordered_set<int> &y = x_group_i->second;
        sorted_x_group.push_back(y);
    }
    
    // now try to form rectangle and count them
    int C = 0;
    for(int i = 0; i < N; i++) {
        unordered_set<int> &y1 = sorted_x_group[i];
        for(int j = i + 1; j < N; j++) {
            unordered_set<int> &y2 = sorted_x_group[j];
            // now match y1 and y2 and count rectangles
            int c = 0;
            for(int y : y1) {
                if(y2.find(y) != y2.end()) {
                    // match found
                    c++;
                }
            }
            // note: i have 3 match points so count is 1+2
            C += (c - 1) * c / 2;
        }
    }
    return C;
}
