// do DFS
void do_dfs(int x, vector<int> G[], int visited[], bool &cycle) {
    if(visited[x] == 1) {
        // already visited
        cycle = true; // cycle found
        return;
    }
    visited[x] = 1; // partially visited
    vector<int> next = G[x];
    for(int node : next) {
        // dfs for every children
        if(visited[node] != 2) {
            // avoid nodes which is already done
            do_dfs(node, G, visited, cycle);
        }
    }
    visited[x] = 2; // fully visited
}

int Solution::solve(int A, vector<int> &B, vector<int> &C) {
    // topological sort (note: using DFS)
    
    vector<int> G[A + 1];
    int visited[A + 1];
    memset(visited, 0, sizeof(visited)); // all nodes not visited yet
    int N = B.size();
    // construct directed graph
    for(int i = 0; i < N; i++) {
        int u = B[i], v = C[i];
        G[u].push_back(v);
    }
    // do DFS for all nodes
    bool cycle = false;
    for(int i = 1; i <= A; i++) {
        if(visited[i] == 2) {
            // fully visited
            continue;
        }
        do_dfs(i, G, visited, cycle);
        if(cycle) {
            // cycle found, no need to proceed
            break;
        }
    }
    
    //cout << cycle << "\n";
    return !cycle; // if no cycle found then not possible
}
