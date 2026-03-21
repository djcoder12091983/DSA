#define NODES 26

// dfs on reverse graph in second pass
void dfs_reverse(int node, vector<int> G_reverse[], bool visited[]) {
    if(visited[node]) {
        // already visited
        return;
    }
    visited[node] = true;
    vector<int> next = G_reverse[node];
    for(int x : next) {
        dfs_reverse(x, G_reverse, visited);
    }
}

// dfs on normal graph in first pass
void dfs(int node, vector<int> G[], bool visited[], stack<int> &last_visited) {
    if(visited[node]) {
        // already visited
        return;
    }
    visited[node] = true;
    vector<int> next = G[node];
    for(int x : next) {
        dfs(x, G, visited, last_visited);
    }
    last_visited.push(node); // done with visiting node
}

int Solution::solve(vector<string> &A) {
    // construct G
    vector<int> G[NODES];
    int in_degree[NODES];
    memset(in_degree, 0, sizeof(in_degree));
    int N = A.size();
    for(int i = 0; i < N; i++) {
        int u = A[i][0] - 97;
        int v = A[i].back() - 97;
        G[u].push_back(v);
        in_degree[v]++;
    }
    
    // find eulerian circuit
    // in degree outdegree check
    bool ok = true;
    for(int i = 0; i < NODES; i++) {
        if(in_degree[i] != G[i].size()) {
            ok = false;
            break;
        }
    }
    if(!ok) {
        // not possible
        return 0;
    }
    
    // find strongly connected components
    bool visited[NODES];
    memset(visited, false, sizeof(visited));
    stack<int> last_visited;
    // fast pass
    for(int i = 0; i < NODES; i++) {
        if(!visited[i] && !G[i].empty()) {
            // start DFS on it
            dfs(i, G, visited, last_visited);
        }
    }
    
    // second pass
    // reverse graph
    vector<int> G_reverse[NODES];
    for(int i = 0; i < N; i++) {
        int u = A[i].back() - 97;
        int v = A[i][0] - 97;
        G_reverse[u].push_back(v);
    }
    memset(visited, false, sizeof(visited));
    // dfs on reverse graph and last_visited nodes
    int c = 0;
    while(!last_visited.empty()) {
        int x = last_visited.top();
        last_visited.pop();
        if(visited[x]) {
            continue;
        }
        dfs_reverse(x, G_reverse, visited);
        c++;
    }
    //cout << c << "\n";
    return c == 1; // only one strongly connected components
}
