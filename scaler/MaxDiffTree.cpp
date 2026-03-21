// global max
int max_diff = 0;

// @ each node find max/min
vector<int> dfs_solve(int parent, int node, vector<int> tree[], vector<int> &A) {
    int x = A[node - 1];
    int max_val = x, min_val = x;
    for(int child : tree[node]) {
        if(child != parent) {
            // avoid loop
            vector<int> max_min = dfs_solve(node, child, tree, A);
            // @ node point get possible maximum difference
            max_diff = max(max_diff, abs(x - max_min[0]));
            max_diff = max(max_diff, abs(x - max_min[1]));
            
            // merged result
            min_val = min(min_val, max_min[0]);
            max_val = max(max_val, max_min[1]);
        }
    }
    
    vector<int> res(2);
    res[0] = min_val, res[1] = max_val;
    return res;
}

int Solution::solve(vector<int> &A, vector<vector<int>> &B) {
    int N = A.size();
    vector<int> tree[N + 1];
    // tree construction
    for(int i = 0; i < N - 1; i++) {
        // both way connected
        int u = B[i][0];
        int v = B[i][1];
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
    max_diff = 0;
    // solve recusively
    dfs_solve(0, 1, tree, A);
    
    return max_diff;
}
