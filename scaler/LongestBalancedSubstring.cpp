// clear stack
void clear(stack<pair<char, int>> &s) {
    while(!s.empty()) {
        s.pop();
    }
}

int Solution::LBSlength(const string A) {
    int N = A.length();
    int DP[N]; // holds LSB @ some point from right side
    memset(DP, 0, sizeof(DP));
    stack<pair<char, int>> track; // track balance factor
    int lbs = 0;
    for(int i = 0; i < N; i++) {
        char x = A[i];
        if(x == '(' || x == '{' || x == '[') {
            track.push({x, i});
        } else {
            if(!track.empty()) {
                pair<char, int> top = track.top();
                char t = top.first;
                bool ok = false;
                if(x == ')') {
                    if(t == '(') {
                        // nutralize
                        ok = true;
                        track.pop();
                    }
                } else if(x == '}') {
                    if(t == '{') {
                        // nutralize
                        ok = true;
                        track.pop();
                    }
                } else {
                    if(t == '[') {
                        // nutralize
                        ok = true;
                        track.pop();
                    }
                }
                if(ok) {
                    int start = top.second;
                    DP[i] = i - start + 1;
                    if(start > 0) {
                        DP[i] += DP[start - 1];
                    }
                } else {
                    // clear stack
                    clear(track);
                }
            }
        }
        
        lbs = max(lbs, DP[i]);
    }
    return lbs;
}
