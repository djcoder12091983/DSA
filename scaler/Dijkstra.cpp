// remove entry on queue
void remove_entry(map<int, unordered_set<int>> &dist_nodes, int dist, int node) {
    dist_nodes[dist].erase(node);
    int x = dist_nodes[dist].size();
    if(x == 0) {
        // fully remove
        dist_nodes.erase(dist);
    }
}

// update on dist queue
void update_entry(map<int, unordered_set<int>> &dist_nodes, int old_dist, int new_dist, int node) {
    remove_entry(dist_nodes, old_dist, node);
    dist_nodes[new_dist].insert(node);
}

vector<int> Solution::solve(int A, vector<vector<int>> &B, int C) {
    // assuming positive edges
    // straight way dijkstra's algorithm
    vector<pair<int, int>> G[A]; // graph
    // build graph
    int N = B.size();
    for(int i = 0; i < N; i++) {
        int u = B[i][0], v = B[i][1], d = B[i][2];
        G[u].push_back({v, d});
        G[v].push_back({u, d});
    }
    // apply single source shortest path algo, (BFS + greedy)
    map<int, unordered_set<int>> dist_nodes; // dist vs nodes
    vector<int> dist_vector(A);
    bool done[A];
    for(int i = 0; i < A; i++) {
        if(i == C) {
            // source node
            dist_vector[i] = 0;
            dist_nodes[0].insert(i);
        } else {
            // initially all are not reachable
            dist_vector[i] = INT_MAX;
            dist_nodes[INT_MAX].insert(i);
        }
        done[i] = false;
    }
    
    // aply BFS + greedy
    while(!dist_nodes.empty()) {
        // choose node greedily
        map<int, unordered_set<int>>::iterator i = dist_nodes.begin();
        int dist = i->first;
        int x = *((i->second).begin());
        if(dist == INT_MAX) {
            // disjoint graph found
            break;
        }
        remove_entry(dist_nodes, dist, x);
        done[x] = true;
        
        // apply BFS
        vector<pair<int, int>> next = G[x];
        for(pair<int, int> child : next) {
            int next_node = child.first;
            int w = child.second;
            if(done[next_node]) {
                // avoid computed nodes
                continue;
            }
            int d = dist_vector[x] + w;
            if(d < dist_vector[next_node]) {
                // new minimun distance
                // update BFS queue
                update_entry(dist_nodes, dist_vector[next_node], d, next_node);
                dist_vector[next_node] = d;
            }
        }
    }
    
    for(int i = 0; i < A; i++) {
        if(dist_vector[i] == INT_MAX) {
            // not reachable
            dist_vector[i] = -1;
        }
    }
    return dist_vector;
}