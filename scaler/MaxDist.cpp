// find suitable position on left side (descending order)
int binary_search(vector<pair<int, int>> &A, int K) {
    int N = A.size();
    int l = 0, r = N - 1;
    int idx = -1;
    while(l <= r) {
        int mid = (l + r) / 2;
        pair<int, int> p = A[mid];
        if(p.first <= K) {
            // take left
            idx = p.second;
            r = mid - 1;
        } else {
            // take right
            l = mid + 1;
        }
    }
    return idx;
}

int Solution::maximumGap(const vector<int> &A) {
    int N = A.size();
    vector<pair<int, int>> t; // descending order
    int max_dist = 0;
    for(int i = 0; i < N; i++) {
        if(t.empty()) {
            t.push_back({A[i], i});
        } else {
            pair<int, int> last = t[t.size() - 1];
            if(last.first <= A[i]) {
                // increasing order
                // find suitable position and find the gap
                int idx = binary_search(t, A[i]);
                max_dist = max(max_dist, i - idx);
            } else {
                // descending order
                t.push_back({A[i], i});
            }
        }
    }
    return max_dist;
}
