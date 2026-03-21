// DFS
void do_dfs(vector<int> &A, int x, bool G[], bool visited[], int &gc) {
    if(G[x]) {
        // already group assigned
        return;
    }
    if(visited[x] && !G[x]) {
        // cycle case
        G[x] = true;
        gc++;
        return;
    }
    visited[x] = true;
    do_dfs(A, A[x] - 1, G, visited, gc);
    G[x] = true;
}

int Solution::solve(vector<int> &A) {
    int gc = 2;
    int N = A.size();
    bool visited[N + 1], G[N + 1];
    memset(visited, false, sizeof(visited));
    memset(G, false, sizeof(G));
    
    G[0] = true;
    for(int i = 1; i < N; i++) {
        do_dfs(A, i, G, visited, gc);
    }
    return gc - 2;
}
