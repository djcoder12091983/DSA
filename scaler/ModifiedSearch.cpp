string Solution::solve(vector<string> &words, vector<string> &Q) {
    // let's solve it using hashing directionary
    // using brute force approach
    unordered_set<string> dict;
    for(string word : words) {
        dict.insert(word);
    }
    
    string ans;
    for(string q : Q) {
        int l = q.length();
        bool ok = false;
        for(int i = 0; i < l; i++) {
            char save = q[i];
            // possible changes
            for(int j = 0; j < 26; j++) {
                if(j == save - 97) {
                    continue;
                }
                q[i] = j + 97;
                // try to look up into dictionary
                if(dict.find(q) != dict.end()) {
                    ok = true;
                    break;
                }
            }
            q[i] = save; // restore to original
            if(ok) {
                break; // found, so no need to find again
            }
        }
        ans.push_back(ok + 48);
    }
    
    return ans;
}
