vector<int> Solution::slidingMaximum(const vector<int> &A, int B) {
    // let's solve it using BST and queue
    // or can be solved using two pointers
    queue<int> Q;
    map<int, int, greater<int>> max_tracker;
    int N = A.size();
    vector<int> window_max;
    for(int i = 0; i < N; i++) {
        if(Q.size() == B) {
            // window is full
            // need to pop from queue and add new
            int first = Q.front();
            // update BST
            max_tracker[first]--;
            if(max_tracker[first] == 0) {
                // remove entry
                max_tracker.erase(first);
            }
            
            Q.pop();
        }
        
        // update BST
        max_tracker[A[i]]++;
        Q.push(A[i]);
        
        if(Q.size() == B) {
            // maximum for current window
            window_max.push_back(max_tracker.begin()->first);
        }
    }
    
    return window_max;
}
