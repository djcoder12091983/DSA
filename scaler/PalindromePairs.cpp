// palindrome check by range
bool palindrome_check(string &t, int l, int r) {
    int i = l, j = r;
    while(i <= j) {
        if(t[i] != t[j]) {
            // not possible
            return false;
        }
        i++;
        j--;
    }
    return true;
}

vector<vector<int>> Solution::solve(vector<string> &A) {
    int N = A.size();
    unordered_map<string, unordered_set<int>> suffix_dict, prefix_dict;
    for(int i = 0; i < N; i++) {
        string t = A[i];
        int l = t.length();
        // add full word
        suffix_dict[t].insert(i);
        // prefix palidrome check
        suffix_dict[t.substr(1, l - 1)].insert(i);
        for(int j = 1; j < l; j++) {
            if(t[0] == t[j]) {
                if(palindrome_check(t, 0, j)) {
                    // add suffix to dict
                    if(j < l - 1) {
                        suffix_dict[t.substr(j + 1, l - j - 1)].insert(i);
                    }
                }
            }
        }
        // suffix palindrome check
        prefix_dict[t.substr(0, l - 1)].insert(i);
        for(int j = l - 2; j >= 0; j--) {
            if(t[l - 1] == t[j]) {
                if(palindrome_check(t, j, l - 1)) {
                    // add prefix to dict
                    prefix_dict[t.substr(0, j)].insert(i);
                }
            }
        }
    }
    // try to form palindrome by pairing
    vector<vector<int>> pairs;
    for(int i = 0; i < N; i++) {
        string t = A[i];
        string rev = string(t.rbegin(),t.rend()); // reverse
        if(suffix_dict.find(rev) != suffix_dict.end()) {
            for(int idx : suffix_dict[rev]) {
                if(idx != i) {
                    // avoid self pairing
                    vector<int> p(2);
                    p[0] = i;
                    p[1] = idx;
                    pairs.push_back(p);
                }
            }
        }
        if(prefix_dict.find(rev) != prefix_dict.end()) {
            for(int idx : prefix_dict[rev]) {
                if(idx != i) {
                    // avoid self pairing
                    vector<int> p(2);
                    p[0] = idx;
                    p[1] = i;
                    pairs.push_back(p);
                }
            }
        }
    }
    return pairs;
}
