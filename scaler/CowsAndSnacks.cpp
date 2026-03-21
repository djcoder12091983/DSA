// dfs
void do_dfs(vector<int> G[], bool visited[], int x, int &c) {
    visited[x] = true; 
    c++;
    for(int next : G[x]) {
        if(!visited[next]) {
            // recursive call
            do_dfs(G, visited, next, c);
        }
    }
}

int Solution::solve(int A, vector<vector<int>> &B) {
    // ref: https://codeforces.com/problemset/problem/1209/D
    vector<int> G[A + 1];
    int N = B.size();
    for(int i = 0; i < N; i++) {
        int u = B[i][0];
        int v = B[i][1];
        G[u].push_back(v);
        G[v].push_back(u);
    }
    
    // do DFS
    int c1 = 0;
    bool visited[A + 1];
    memset(visited, false, sizeof(visited));
    for(int x = 1; x <= A; x++) {
        if(!visited[x]) {
            int c = 0;
            do_dfs(G, visited, x, c);
            c1 += c - 1;
        }
    }
    return N - c1;
}
