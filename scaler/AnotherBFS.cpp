int Solution::solve(int A, vector<vector<int>> &B, int C, int D) {
    // draw graph
    // note: split 2 into 2 one(s)
    int N = B.size();
    int dummy_node = A;
    // find how many nodes will be required
    for(int i = 0; i < N; i++) {
        if(B[i][2] == 2) {
            dummy_node++;
        }
    }
    // draw graph having edge 2 splitted with 2 one(s)
    int limit = dummy_node;
    dummy_node = A;
    vector<int> G[limit];
    for(int i = 0; i < N; i++) {
        int u = B[i][0];
        int v = B[i][1];
        int d = B[i][2];
        // both way
        if(d == 1) {
            G[u].push_back(v);
            G[v].push_back(u);
        } else {
            // split 2 into 2 one(s)
            G[u].push_back(dummy_node);
            G[dummy_node].push_back(u);
            G[dummy_node].push_back(v);
            G[v].push_back(dummy_node);
            dummy_node++;
        }
    }
    // BFS to find shortest path (having edge positive weight 1/2)
    int dist[limit];
    for(int i = 0; i < limit; i++) {
        dist[i] = -1;
    }
    dist[C] = 0;
    queue<int> bfs;
    bfs.push(C);
    bool ok = false;
    while(!bfs.empty()) {
        int x = bfs.front();
        bfs.pop();
        // next node to explore
        vector<int> next = G[x];
        for(int child : next) {
            if(dist[child] == -1) {
                // not visited yet
                dist[child] = 1 + dist[x];
                if(child == D) {
                    // destination reached
                    ok = true;
                    break;
                }
                bfs.push(child);
            }
        }
        if(ok) {
            // destination reached
            break;
        }
    }
    
    return dist[D];
}
