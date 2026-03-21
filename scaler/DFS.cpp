bool reachable(vector<int> *G, bool *visited, int X, int Y) {
    visited[Y] = true;
    vector<int> nodes = G[Y];
    bool ok = false;
    for(int node : nodes) {
        if(node == X) {
            ok |= true; // destination reached
            break;
        }
        if(!visited[node]) {
            ok |= reachable(G, visited, X, node); // DFS
        }
    }
    
    return ok;
}

int Solution::solve(vector<int> &G, const int X, const int Y) {
    if(X == Y) {
        // best case
        return true;
    }
    // draw graph
    int N = G.size();
    vector<int> graph[N + 1];
    bool visited[N + 1];
    memset(visited, false, sizeof(visited));
    for(int i = 0; i < N; i++) {
        int u = G[i];
        int v = i + 1;
        graph[u].push_back(v); // one way
    }
    // DFS to check path exists from y to x
    return reachable(graph, visited, X, Y);
}
