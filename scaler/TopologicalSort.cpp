vector<int> Solution::solve(int A, vector<vector<int>> &B) {
    // will use BFS with a trick, from BFS queue we will choose smallest one
    
    vector<int> G[A + 1];
    int in_degree[A + 1], out_degree[A + 1];
    memset(in_degree, 0, sizeof(in_degree));
    memset(out_degree, 0, sizeof(out_degree));
    set<int> bfs_queue;
    // construct directed graph and populate intial bfs_queue
    int N = B.size();
    for(int i = 0; i < N; i++) {
        int u = B[i][0], v = B[i][1];
        G[u].push_back(v);
        in_degree[v]++;
        out_degree[u]++;
    }
    for(int i = 1; i <= A; i++) {
        if(in_degree[i] == 0) {
            bfs_queue.insert(i);
        }
    }
    
    vector<int> t_sorting;
    while(!bfs_queue.empty()) {
        // choose smallest possible
        int x = *bfs_queue.begin();
        bfs_queue.erase(x);
        
        t_sorting.push_back(x); // update sorted array
        // explore children and remove edges and populate next bfs nodes
        vector<int> next = G[x];
        for(int child : next) {
            in_degree[child]--; // remove edge
            out_degree[x]--;
            if(in_degree[child] == 0) {
                bfs_queue.insert(child); // next bfs_queue node
            }
        }
    }
    // check for cycle
    bool cycle = false;
    for(int i = 1; i <= A; i++) {
        if(in_degree[i] || out_degree[i]) {
            cycle = true;
            break;
        }
    }
    
    if(cycle) {
        // not possible
        t_sorting.clear();
    }
    
    return t_sorting;
}