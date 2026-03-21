#define INF INT_MAX

vector<vector<int>> Solution::solve(vector<vector<int>> &A) {
    // build the graph
    int N = A.size();
    vector<vector<int>> dist;
    for(int i = 0; i < N; i++) {
        vector<int> r;
        for(int j = 0; j < N; j++) {
            if(i != j) {
                if(A[i][j] != -1) {
                    // direct edge
                    r.push_back(A[i][j]);
                } else {
                    // intially not reachable
                    r.push_back(INF);
                }
            } else {
                // source and destination same
                r.push_back(0);
            }
        }
        dist.push_back(r);
    }
    
    // apply "Floyd Warshall"
    for(int k = 0; k < N; k++) {
        // from i to j consider each possibility k
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(i != j) {
                    if(dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }
    
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            if(dist[i][j] == INF) {
                // not reachable
                dist[i][j] = -1;
            }
        }
    }
    return dist;
}
