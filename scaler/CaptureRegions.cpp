void Solution::solve(vector<vector<char>> &A) {
    // find connected components with all zeros
    // now zeros will be converted into X if they don't exist @ end of any any side
    int N = A.size();
    int M = A[0].size();
    queue<pair<int, int>> zeros;
    bool visited[N][M];
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < M; j++) {
            if(A[i][j] == 'O') {
                zeros.push({i, j});
            }
            visited[i][j] = false;
        }
    }
    
    // directions for BFS
    pair<int, int> directions[4] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    while(!zeros.empty()) {
        pair<int, int> z = zeros.front();
        int x = z.first, y = z.second;
        zeros.pop();
        if(visited[x][y]) {
            // no need to continue with this
            continue;
        }
        queue<pair<int, int>> bfs;
        // start with unvisited zero do BFS
        bfs.push(z);
        visited[x][y] = true;
        // check point is @ end of any sides
        bool end = x == 0 || y == 0 || x == N - 1 || y == M - 1;
        vector<pair<int, int>> t;
        t.push_back(z);
        while(!bfs.empty()) {
            z = bfs.front();
            bfs.pop();
            x = z.first, y = z.second;
            for(pair<int, int> d : directions) {
                int i = x + d.first, j = y + d.second;
                if(i >= 0 && i < N && j >= 0 && j < M && A[i][j] == 'O' && !visited[i][j]) {
                    // valid move
                    visited[i][j] = true;
                    z = {i, j};
                    bfs.push(z);
                    end |= i == 0 || j == 0 || i == N - 1 || j == M - 1;
                    t.push_back(z);
                }
            }
        }
        if(!end) {
            // all points in middle
            // so we can turn them into X
            for(pair<int, int> p : t) {
                A[p.first][p.second] = 'X';
            }
        }
    }
}
