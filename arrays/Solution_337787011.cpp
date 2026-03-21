class Solution {
private:
    int r, c, l;
    
    // DFS search on matrix
    bool dfs_search(vector<vector<char>>& B, int x, int y, string &W, int idx,
                    vector<vector<bool>>& visited) {
        //cout << x << " " << y << " " << idx << "\n";
        visited[x][y] = 1; // marked as visited
        if(idx + 1 == l) {
            // found
            return true;
        }
        
        char next = W[idx + 1];
        // 4 possible moves
        int next_moves[][2] = {
            {x, y + 1},
            {x, y - 1},
            {x + 1, y},
            {x - 1, y}
        };
        bool ok = false;
        for(int i = 0; i < 4; i++) {
            int next_x = next_moves[i][0];
            int next_y = next_moves[i][1];
            if(next_x >= 0 && next_x < r && next_y >= 0 && next_y < c) {
                if(!visited[next_x][next_y] && B[next_x][next_y] == next) {
                    // valid move
                    ok = dfs_search(B, next_x, next_y, W, idx + 1, visited);
                    if(ok) {
                        // found already
                        break;
                    }
                }
            }
        }
        if(!ok) {
            // can be visited again
            visited[x][y] = false;
        }
        return ok;
    }
public:
    bool exist(vector<vector<char>>& B, string W) {
        queue<pair<int, int>> start_p;
        l = W.length();
        char first = W[0];
        r = B.size();
        c = B[0].size();
        vector<vector<bool>> visited(r);
        
        // find start position
        for(int i = 0; i < r; i++) {
            visited[i] = vector<bool>(c);
            for(int j = 0; j < c; j++) {
                if(B[i][j] == first) {
                    start_p.push({i, j});
                }
                visited[i][j] = false;
            }
        }
        
        // dfs call to search word for each poisition
        bool ok = false;
        while(!start_p.empty()) {
            pair<int, int> p = start_p.front();
            int x = p.first, y = p.second;
            ok = dfs_search(B, x, y, W, 0, visited);
            if(ok) {
                // found
                break;
            }
            start_p.pop();
        }
        return ok;
    }
};