#define MOD 1000000007L
typedef long long int ll;

bool comp_edge(pair<int, char> e1, pair<int, char> e2) {
    return e1.first < e2.first;
}

int Solution::solve(vector<int> &A, vector<int> &B) {
    // note: sort based on A and B when minimum edge selected
    // then how many count we can use based on Horizontal/Vecrtical type
    int H = A.size(), V = B.size();
    
    vector<pair<int, char>> E; // stores edges with type Horizontal/Vertical
    for(int i = 0; i < H; i++) {
        E.push_back({A[i], 'H'});
    }
    for(int i = 0; i < V; i++) {
        E.push_back({B[i], 'V'});
    }
    // sort them
    sort(E.begin(), E.end(), comp_edge);
    
    // MST, greedy choosing edges and avoid loop
    // now choose edge one by one then count how many times it will be used
    // based on type Horizontal/Vertical
    int h = 0, v = 0;
    H++, V++;
    int N = E.size();
    ll C = 0L;
    for(int i = 0; i < N; i++) {
        pair<int, char> e = E[i];
        ll w = e.first;
        char t = e.second;
        //cout << w << " -> " << t << "\n";
        if(t == 'H') {
            // horizontal
            C += w * (V - v);
            if(C >= MOD) {
                C %= MOD;
            }
            h++; // block horizontals to avoid loop
        } else {
            // vertical
            C += w * (H - h);
            if(C >= MOD) {
                C %= MOD;
            }
            v++; // block vecrticals to avoid loop
        }
    }
    
    return C % MOD;
}