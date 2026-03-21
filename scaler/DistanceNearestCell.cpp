vector<vector<int>> Solution::solve(vector<vector<int>> &A) {
    int N = A.size();
    int M = A[0].size();
    int dist[N][M];
    // note: shortest path using BFS, having one unit edge distance
    queue<pair<int, int>> bfs;
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            if(A[i][j] == 1) {
                // these are starting points
                dist[i][j] = 0;
                bfs.push({i, j});
            } else {
                // not computed yet
                dist[i][j] = -1;
            }
        }
    }
    pair<int, int> moves[4] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    // do BFS from every 1
    while(!bfs.empty()) {
        pair<int, int> X = bfs.front();
        bfs.pop();
        int i = X.first, j = X.second;
        // next valid moves
        for(pair<int, int> move: moves) {
            int nexti = i + move.first, nextj = j + move.second;
            if(nexti >= 0 && nexti < N && nextj >= 0 && nextj < M && dist[nexti][nextj] == -1) {
                // valid move
                dist[nexti][nextj] = dist[i][j] + 1;
                bfs.push({nexti, nextj});
            }
        }
    }
    // now we are done with all points
    vector<vector<int>> ans_matrix;
    for(int i = 0; i < N; i++) {
        vector<int> row;
        for(int j = 0; j < M; j++) {
            row.push_back(dist[i][j]);
        }
        ans_matrix.push_back(row);
    }
    return ans_matrix;
}