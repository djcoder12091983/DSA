vector<int> Solution::solve(int A, int B, int C, vector<int> &D, vector<int> &E, vector<int> &F, vector<int> &G, vector<int> &H) {
    // simple "Floyd Warshall Algorithm"
    
    // base cases
    int dist[A + 1][A + 1];
    for(int i = 1; i <= A; i++) {
        for(int j = 1; j <= A; j++) {
            if(i == j) {
                dist[i][j] = 0;
            } else {
                dist[i][j] =  INT_MAX;
            }
        }
    }
    
    // update dist matrix as graph edges
    for(int i = 0; i < B; i++) {
        int u = D[i], v = E[i], x = F[i];
        if(u != v) {
            // avoid self loop
            // bidirectional
            dist[u][v] = min(dist[u][v], x);
            dist[v][u] = min(dist[v][u], x);
        }
    }
    
    // preporcessing, apply "Floyd Warshall Algorithm"
    for(int k = 1; k <= A; k++) {
        // from i->j all possibilities k
        for(int i = 1; i <= A; i++) {
            for(int j = 1; j <= A; j++) {
                if(i == j) {
                    // avoid
                    continue;
                }
                if(dist[i][k] != INT_MAX && dist[k][j] != INT_MAX) {
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
    
    // solve queries
    vector<int> ans;
    for(int i = 0; i < C; i++) {
        int u = G[i], v = H[i];
        if(dist[u][v] == INT_MAX) {
            // not reachable
            ans.push_back(-1);
        } else {
            // path exists
            ans.push_back(dist[u][v]);
        }
    }
    
    return ans;
}