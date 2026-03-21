#define MOD 998244353
typedef long long int ll;

// try to label odd/even in DFS manner
bool do_dfs(vector<int> G[], int x, int label, int mark[], int labels[]) {
    mark[x] = label;
    labels[label]++;
    vector<int> next = G[x];
    int next_label = (label + 1) % 2; // next label
    bool ok = true;
    for(int child : next) {
        if(mark[child] == -1) {
            // note yet visited, recursive DFS
            if(!do_dfs(G, child, next_label, mark, labels)) {
                // not possible
                ok = false;
                break;
            }
        } else {
            // check whether alternate label happens
            if(mark[child] != next_label) {
                // not possible
                ok = false;
                break;
            }
        }
    }
    return ok;
}

int Solution::solve(int A, vector<vector<int>> &B) {
    vector<int> G[A + 1];
    // build graph
    int N = B.size();
    for(int i = 0; i < N; i++) {
        int u = B[i][0];
        int v = B[i][1];
        G[u].push_back(v);
        G[v].push_back(u);
    }
    // try to label with odd/even alternatively
    // DFS
    int mark[A + 1]; // -1 means not visited, 0 even and 1 odd label
    memset(mark, -1, sizeof(mark));
    // graph may be disconnected
    bool ok = true;
    ll ways = 1L;
    for(int x = 1; x <= A; x++) {
        if(mark[x] != -1) {
            // already porcessed
            continue;
        }
        int labels[2];
        memset(labels, 0, sizeof(labels));
        if(!do_dfs(G, x, 0, mark, labels)) {
            // not possible to label
            ok = false;
            break;
        } else {
            ll c = 0L;
            for(int i = 0; i < 2; i++) {
                int l = labels[i];
                ll t = 1L;
                for(int j = 0; j < l; j++) {
                    t *= 2;
                    if(t >= MOD) {
                        t %= MOD;
                    }
                }
                c = (c + t) % MOD;
            }
            ways = (ways * c) % MOD;
        }
    }
    if(ok) {
        // possible
        return ways;
    } else {
        // not possible
        return 0;
    }
}