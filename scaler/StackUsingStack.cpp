vector<int> Solution::solve(vector<int> &A) {
    stack<int> S1, S2;
    for(int a : A) {
        S1.push(a);
    }
    vector<int> sorted;
    while(!S1.empty()) {
        int min = INT_MAX;
        // extract min using S2
        while(!S1.empty()) {
            int t = S1.top();
            S1.pop();
            if(t < min) {
                min = t;
            }
            S2.push(t);
        }
        sorted.push_back(min);
        // remaining, push back to S1
        while(!S2.empty()) {
            int t = S2.top();
            S2.pop();
            if(t == min) {
                // skip it
                break;
            }
            S1.push(t);
        }
        while(!S2.empty()) {
            S1.push(S2.top());
            S2.pop();
        }
    }
    
    return sorted;
}
