vector<string> Solution::letterCombinations(string A) {
    // simple cross product
    map<int, vector<char>> digit_map;
    digit_map[0] = vector<char>{'0'};
    digit_map[1] = vector<char>{'1'};
    digit_map[2] = vector<char>{'a', 'b', 'c'};
    digit_map[3] = vector<char>{'d', 'e', 'f'};
    digit_map[4] = vector<char>{'g', 'h', 'i'};
    digit_map[5] = vector<char>{'j', 'k', 'l'};
    digit_map[6] = vector<char>{'m', 'n', 'o'};
    digit_map[7] = vector<char>{'p', 'q', 'r', 's'};
    digit_map[8] = vector<char>{'t', 'u', 'v'};
    digit_map[9] = vector<char>{'w', 'x', 'y', 'z'};
    
    // process input
    vector<string> bfs;
    bfs.push_back(""); // start with empty string
    int l = A.length();
    for(int i = 0; i < l; i++) {
        vector<string> next_bfs;
        int l1 = bfs.size();
        for(int j = 0; j < l1; j++) {
            int l2 = digit_map[A[i] - 48].size();
            for(int k = 0; k < l2; k++) {
                string new_t;
                new_t.append(bfs[j]);
                new_t.push_back(digit_map[A[i] - 48][k]);
                
                next_bfs.push_back(new_t);
            }
        }
        
        bfs = next_bfs;
    }
    
    // lexographically sort
    sort(bfs.begin(), bfs.end());
    
    return bfs;
}
