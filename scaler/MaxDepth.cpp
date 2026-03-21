// DFS to store level wise values
void dfs(int x, int p, int l, vector<int> t[], vector<int> &D, unordered_map<int, set<int>> &v, int &h) {
    
    h = max(h, l);
    v[l].insert(D[x - 1]);
    vector<int> next = t[x];
    for(int child : next) {
        if(child != p) {
            // avoid loop
            dfs(child, x, l + 1, t, D, v, h);
        }
    }
}

vector<int> Solution::solve(int A, vector<int> &B, vector<int> &C, vector<int> &D, vector<int> &E, vector<int> &F) {
    // logic is quite straight forward
    // store values for each level in sorted order to do BS on it to find answer
    
    // draw tree
    vector<int> tree[A + 1];
    int N = B.size();
    for(int i = 0; i < N; i++) {
        int u = B[i], v = C[i];
        // both way connected
        tree[u].push_back(v);
        tree[v].push_back(u);
    }
    // do DFS to store values level wise
    unordered_map<int, set<int>> l_values;
    int max_height = 0;
    dfs(1, 0, 0, tree, D, l_values, max_height);
    
    // answer queries
    N = E.size();
    max_height++;
    vector<int> ans;
    for(int i = 0; i < N; i++) {
        int L = E[i] % max_height;
        int X = F[i];
        auto closed = l_values[L].lower_bound(X);
        if(closed == l_values[L].end()) {
            // not found
            ans.push_back(-1);
        } else {
            // found
            ans.push_back(*closed);
        }
    }
    return ans;
}