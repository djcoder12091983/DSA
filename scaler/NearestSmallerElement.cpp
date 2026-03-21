vector<int> Solution::prevSmaller(vector<int> &A) {
    int N = A.size();
    vector<int> ans(N);
    // a little bit of twist in next greater element
    stack<pair<int, int>> track; // track number along with index
    // note: scan from end
    for(int i = N - 1; i >= 0; i--) {
        if(track.empty()) {
            // straight way push
            track.push({A[i], i});
        } else {
            if(A[i] < track.top().first) {
                // sequence break
                while(!track.empty()) {
                    pair<int, int> top = track.top();
                    if(A[i] < top.first) {
                        // found next smaller behind
                        ans[top.second] = A[i];
                    } else {
                        // back to sequence
                        break;
                    }
                    track.pop();
                }
            }
            track.push({A[i], i});
        }
    }
    // populate for remaining
    while(!track.empty()) {
        ans[track.top().second] = -1; // not found
        track.pop();
    }
    
    return ans;
}
