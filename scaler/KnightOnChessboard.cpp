int Solution::knight(int A, int B, int C, int D, int E, int F) {
    if(E > A || F > B) {
        // outside the board
        // straight way can be said
        return -1;
    }
    // single sorce shortest path using BFS
    // note: having one unit positive distance b2n nodes
    
    // possible moves for a given point
    pair<int, int> moves[8] = {
        {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
        {1, -2}, {1, 2}, {2, -1}, {2, 1}
    };
    
    int dist[A + 1][B + 1];
    for(int i = 0; i <= A; i++) {
        for(int j = 0; j <= B; j++) {
            dist[i][j] = -1;
        }
    }
    queue<pair<int, int>> bfs;
    bfs.push({C, D}); // starts with C,D
    dist[C][D] = 0;
    bool done = false;
    while(!bfs.empty()) {
        pair<int, int> X = bfs.front();
        bfs.pop();
        int i = X.first, j = X.second;
        // possible valid moves
        for(pair<int, int> move: moves) {
            int nexti = i + move.first, nextj = j + move.second;
            if(nexti >= 1 && nexti <= A && nextj >= 1 && nextj <= B && dist[nexti][nextj] == -1) {
                // vaid move
                bfs.push({nexti, nextj});
                dist[nexti][nextj] = dist[i][j] + 1;
                if(nexti == E && nextj == F) {
                    // we are done
                    done = true;
                    break;
                }
            }
        }
        if(done) {
            break;
        }
    }
    
    return dist[E][F]; // returns computed shortest distance
}