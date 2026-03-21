// ref: <tutorialspoint.dev>/data-structure/graph-data-structure/find-minimum-weight-cycle-undirected-graph
#include<list> 
#define INF 0x3f3f3f3f 

struct Edge {
    int u;
    int v;
    int w;
};
  
// undirected wieghted graph
class Graph {
    int X;
    list <pair<int, int >> *next; 
    vector<Edge> edges;

public:
    Graph(int X) {
        this->X = X;
        next = new list<pair<int, int>>[X]; 
    } 
  
    // add edge to graph 
    void add_edge( int u, int v, int w ) {
        next[u].push_back(make_pair(v, w));
        next[v].push_back(make_pair( u, w));
        // add edge 
        edges.push_back({u, v, w});
    }
  
    // remove edge
    void remove_edge( int u, int v, int w) {
        next[u].remove(make_pair(v, w));
        next[v].remove(make_pair(u, w));
    } 
  
    // Dijkstra’s uhortest path algorithm (u->v)
    int path(int u, int v) {
        set<pair<int, int>> setds;
        // all distances as infinite (INF) 
        vector<int> dist(X, INF);
        // source
        setds.insert(make_pair(0, u));
        dist[u] = 0;
      
        // explore all nodes
        while(!setds.empty()) {
            pair<int, int> t = *(setds.begin());
            setds.erase(setds.begin());
      
            int x = t.second; 
      
            list<pair<int, int>>::iterator i;
            for(i = next[x].begin(); i != next[x].end(); ++i) {
                int y = i->first; 
                int w = i->second; 
      
                // find shoter path 
                if(dist[y] > dist[x] + w) {
                    // updating distance
                    if(dist[y] != INF) {
                        setds.erase(setds.find(make_pair(dist[y], y)));
                    }
                    dist[y] = dist[x] + w;
                    setds.insert(make_pair(dist[y], y));
                }
            }
        }
        return dist[v];
    }
    
    // minimum weighted cycle 
    int min_cycle() {
        int min_c = INT_MAX;
        int E = edges.size();
        for(int i = 0; i < E; i++) {
            Edge e = edges[i];
            // remove edge and calculate cycle distance for this edge
            remove_edge(e.u, e.v, e.w);
            int min_d = path(e.u, e.v);
            min_c = min(min_c, min_d + e.w);
            // add edge back to the graph
            add_edge(e.u, e.v, e.w);
        }
      
        // return min cycle 
        return min_c; 
    }
};

int Solution::solve(int A, vector<vector<int>> &B) {
    // build graph and find min cycle
    Graph G(A);
    int N = B.size();
    for(int i = 0; i < N; i++) {
        int u = B[i][0], v = B[i][1], w = B[i][2];
        G.add_edge(u - 1, v - 1, w);
    }
    
    // find min cycle
    return G.min_cycle();
}
