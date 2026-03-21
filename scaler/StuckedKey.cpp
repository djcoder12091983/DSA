vector<pair<char, int>> compress(string &x) {
    int N = x.length();
    char t = x[0];
    int c = 1;
    vector<pair<char, int>> compressed;
    for(int i = 1; i < N; i++) {
        if(x[i] != t) {
            // sequence break
            compressed.push_back({t, c});
            
            // return
            t = x[i];
            c = 1;
        } else {
            c++;
        }
    }
    // last
    compressed.push_back({t, c});
    
    return compressed;
}

// b can be formed from a
bool compare(vector<pair<char, int>> &a, vector<pair<char, int>> &b) {
    int l1 = a.size();
    int l2 = b.size();
    if(l1 != l2) {
        // not possible
        return false;
    } else {
        for(int i = 0; i < l1; i++) {
            pair<char, int> x1 = a[i];
            pair<char, int> x2 = b[i];
            
            if(x1.first != x2.first) {
                // not possible
                return false;
            }
            if(x1.second > x2.second) {
                // not possible
                return false;
            } 
        }
    }
    
    return true;
}

int Solution::solve(vector<string> &A, string B) {
    vector<pair<char, int>> compressed_b = compress(B);
    
    int N = A.size();
    for(int i = 0; i < N; i++) {
        vector<pair<char, int>> compressed_a = compress(A[i]);
        bool ok = compare(compressed_a, compressed_b);
        if(ok) {
            // found
            return true;
        }
    }
    
    return false; // not found yet
}
