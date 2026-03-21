string Solution::minWindow(string A, string B) {
    int b_l = B.length();
    unordered_map<char, int> b_f;
    for(int i = 0; i < b_l; i++) {
        b_f[B[i]]++;
    }
    int N = A.length();
    int i = 0;
    int start = -1;
    unordered_map<char, int> a_f;
    while(i < N) {
        if(b_f.find(A[i]) != b_f.end()) {
            // first occurence found
            start = i;
            break;
        }
        i++;
    }
    if(start == -1) {
        // not possible
        return "";
    }
    int min_l = INT_MAX, c = 0;
    int min_s = 0, min_e = -1;
    for(int j = i; j < N; j++) {
        char a = A[j];
        if(b_f.find(a) != b_f.end()) {
            // tentative match
            a_f[a]++;
            if(a_f[a] <= b_f[a]) {
                c++;
            } else {
                if(A[start] == a) {
                    // can be removed from left side
                    for(int k = start; k <= j; k++) {
                        char a_k = A[k];
                        if(b_f.find(a_k) == b_f.end()) {
                            // ignore unwanted characters
                            continue;
                        }
                        if(a_f[a_k] > b_f[a_k]) {
                            a_f[a_k]--;
                        } else {
                            start = k; // new start
                            break;
                        }
                    }
                }
            }
            
            if(c >= b_l) {
                // once c >= b_l then every possible subtring
                // will be verified for it's minimal result
                int l = j - start + 1;
                if(l < min_l) {
                    // new minimum
                    min_l = l;
                    min_s = start;
                    min_e = j;
                }
            }
        }
    }
    
    string min_str;
    for(i = min_s; i <= min_e; i++) {
        min_str.push_back(A[i]);
    }
    
    return min_str;
}
