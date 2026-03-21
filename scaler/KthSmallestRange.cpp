// note: <gfg>/kth-smallest-element-in-a-subarray/
// build segment tree
void build(vector<int> seg_tree[], int idx, int start, int end, pair<int, int>* B) {
    if(start == end) {
        // leaf
        seg_tree[idx].push_back(B[start].second);
        return;
    }
    int mid = (start + end) / 2;
    build(seg_tree, 2 * idx + 1, start, mid, B);
    build(seg_tree, 2 * idx + 2, mid + 1, end, B);
    // merge sorted positions
    merge(seg_tree[2 * idx + 1].begin(), seg_tree[2 * idx + 1].end(),
    seg_tree[2 * idx + 2].begin(), seg_tree[2 * idx + 2].end(),
    back_inserter(seg_tree[idx]));
}
  
// kth smallest element in range 
int query(vector<int> seg_tree[], int idx, int start, int end, int l, int r, int k) {
    if(start == end) {
        return seg_tree[idx][0];
    }
    // how many elements belong to this range
    int p =  upper_bound(seg_tree[2 * idx + 1].begin(), seg_tree[2 * idx + 1].end(), r)
                - lower_bound(seg_tree[2 * idx + 1].begin(), seg_tree[2 * idx + 1].end(), l);
  
    int mid = (start + end) / 2;
    if(p >= k) {
        // solution exists on left side
        return query(seg_tree, 2 * idx + 1, start, mid, l, r, k);
    } else {
        // find remaining on right side
        return query(seg_tree, 2 * idx + 2, mid + 1, end, l, r, k - p);
    }
}

vector<int> Solution::solve(vector<int> &A, vector<vector<int>> &B) {
    int N = A.size();
    pair<int, int> data[N];
    for(int i = 0; i < N; i++) {
        data[i] = {A[i], i};
    }
    // sort data
    sort(data, data + N);
    vector<int> seg_tree[1000000];
    // build segment tree
    build(seg_tree, 0, 0, N - 1, data);
    // query
    int Q = B.size();
    vector<int> ans;
    for(int i = 0; i < Q; i++) {
        int l = B[i][0];
        int r = B[i][1];
        int k = B[i][2];
        // kth smallest element in range
        ans.push_back(A[query(seg_tree, 0, 0, N - 1, l - 1, r - 1, k)]);
    }
    return ans;
}
