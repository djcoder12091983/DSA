class Solution {
private:
    map<int, unordered_set<int>> d2n; // distance to node
    unordered_map<int, int> n2d; // node 2 distance
    
    // update node distance
    void update_dist(int node, int d) {
        // remove existing if any
        remove_node(node);
        
        // update with new one
        n2d[node] = d;
        d2n[d].insert(node);
    }
    
    // remove node data
    void remove_node(int node) {
        if(n2d.find(node) != n2d.end()) {
            int old_d = n2d[node];
            n2d.erase(node);
            unordered_set<int> &nodes = d2n[old_d];
            nodes.erase(node);
            if(nodes.empty()) {
                // full remove
                d2n.erase(old_d);
            }
        }
    }
public:
    int networkDelayTime(vector<vector<int>>& times, int N, int K) {
        // prepare graph
        vector<pair<int, int>> graph[N + 1];
        int l = times.size();
        for(int i = 0; i < l; i++) {
            vector<int> detail = times[i];
            int u = detail[0];
            int v = detail[1];
            int d = detail[2];
            graph[u].push_back({v, d});
        }
        
        // dijkstra's shortest path algorithm with positive edges
        int T[N + 1];
        for(int i = 1; i <= N; i++) {
            if(i != K) {
                // others node
                T[i] = INT_MAX;
            } else {
                // source node
                T[i] = 0;
                update_dist(i, T[i]);
            }
        }
        
        bool visited[N + 1];
        memset(visited, false, sizeof(visited));
        // BFS greedy
        while(!d2n.empty()) {
            map<int, unordered_set<int>>::iterator i = d2n.begin();
            int u_d = i->first;
            unordered_set<int> nodes = i->second;
            int u = *nodes.begin();
            visited[u] = true; // mark as visited
            
            // cout << "T1: " << u << " : " << u_d << " D: " << T[u] << "\n";
            
            // remove node after work
            remove_node(u);
            
            // BFS
            vector<pair<int, int>> adjacent = graph[u];
            for(pair<int, int> detail : adjacent) {
                int v = detail.first;
                if(visited[v]) {
                    // already visited
                    // avoid cycle
                    continue;
                }
                int v_d = detail.second;
                T[v] = min(T[v], u_d + v_d);
                // cout << "T2: " << v << ": " << v_d << " D: " << T[v] << "\n";
                
                // update distance
                update_dist(v, T[v]);
            }
        }
        
        int tt = INT_MIN;
        for(int i = 1; i <= N; i++) {
            tt = max(tt, T[i]);
        }
        if(tt == INT_MAX) {
            tt = -1;
        }
        
        return tt;
    }
};