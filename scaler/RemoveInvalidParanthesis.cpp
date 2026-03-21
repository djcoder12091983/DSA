// count set bits
int c1(int N) {
    int c = 0; 
    while(N) { 
        c += N & 1; 
        N >>= 1; 
    } 
    return c;
}

// is balance check
bool is_balance(string &A) {
    int l = A.length();
    int bf = 0;
    for(int i = 0; i < l; i++) {
        if(A[i] == '(') {
            bf++;
        } else if(A[i] == ')') {
            if(bf) {
                bf--;
            }
        }
    }
    return bf == 0;
}

vector<int> find_removes(int N, int c) {
    vector<int> removes;
    for(int i = 0; i < N; i++) {
        if(c1(i) == c) {
            removes.push_back(i);
        }
    }
    return removes;
}

void find_invalid(int mask, vector<int> &A, unordered_set<int> &invalid) {
    int l = A.size();
    for(int i = 0; i < l; i++) {
        if(mask & (1 << i)) {
            // set bit
            invalid.insert(A[i]);
        }
    }
}

vector<string> Solution::solve(string A) {
    int left = 0, right = 0;
    int l = A.length();
    int bf = 0;
    // checking balance details
    vector<int> left_p, right_p;
    for(int i = 0; i < l; i++) {
        if(A[i] == '(') {
            left_p.push_back(i);
            bf++;
        } else if(A[i] == ')') {
            right_p.push_back(i);
            if(bf) {
                bf--;
            } else {
                right++;
            }
        }
    }
    left = bf;
    
    // find possible removes to form valid sequence
    vector<int> left_removes = find_removes(1 << left_p.size(), left);
    vector<int> right_removes = find_removes(1 << right_p.size(), right);
    
    unordered_set<string> t_result;
    for(int i : left_removes) {
        for(int j : right_removes) {
            unordered_set<int> invalid_p;
            find_invalid(i, left_p, invalid_p);
            find_invalid(j, right_p, invalid_p);
            
            // try to form a valid sequence discarding invalid positions
            string t;
            for(int x = 0; x < l; x++) {
                if(invalid_p.find(x) == invalid_p.end()) {
                    // valid position to add
                    t.push_back(A[x]);
                }
            }
            if(is_balance(t)) {
                // valid sequence
                t_result.insert(t);
            }
        }
    }
    
    vector<string> ans;
    for(string a : t_result) {
        ans.push_back(a);
    }
    return ans;
}
