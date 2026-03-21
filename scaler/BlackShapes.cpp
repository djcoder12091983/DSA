int Solution::black(vector<string> &A) {
    // do BFS per node and count how many connected components
    int N = A.size();
    int M = A[0].length();
    bool visited[N][M];
    memset(visited, false, sizeof(visited));
    int cc = 0;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            if(A[i][j] == 'X' && !visited[i][j]) {
                // do BFS
                queue<pair<int, int>> bfs;
                bfs.push({i, j});
                visited[i][j] = true;
                while(!bfs.empty()) {
                    pair<int, int> node = bfs.front();
                    bfs.pop();
                    int u = node.first, v = node.second;
                    pair<int, int> next[] = {
                       {u - 1, v},
                       {u, v - 1},
                       {u + 1, v},
                       {u, v + 1}
                    };
                    // possible choices
                    for(int k = 0; k < 4; k++) {
                        u = next[k].first;
                        v = next[k].second;
                        if(u >= 0 && u < N && v >= 0 && v < M && A[u][v] == 'X' && !visited[u][v]) {
                            // valid move
                            bfs.push({u, v});
                            visited[u][v] = true;
                        }
                    }
                }
                cc++; // island
            }
        }
    }
    
    return cc;
}