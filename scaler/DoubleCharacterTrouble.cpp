string Solution::solve(string A) {
    int N = A.length();
    stack<char> track;
    for(int i = 0; i < N; i++) {
        if(track.empty()) {
            track.push(A[i]);
        } else {
            if(A[i] == track.top()) {
                // consecutive pair
                track.pop();
            } else {
                track.push(A[i]);
            }
        }
    }
    // final result
    string ans;
    while(!track.empty()) {
        ans.push_back(track.top());
        track.pop();
    }
    // reverse string as popped from stack
    reverse(ans.begin(), ans.end()); 
    return ans;
}
