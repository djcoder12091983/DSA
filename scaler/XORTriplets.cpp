#define MOD 1000000007
typedef long long int ll;

// count from positions
ll count(vector<int> &P) {
    int N = P.size();
    ll C = 0;
    // how many ways triplets can be formed
    for(int i = 0; i < N - 1; i++) {
        int d = P[i + 1] - P[i];
        C += d * (N - i - 1) * (i + 1);
    }
    C -=  N * (N - 1) / 2;
    //cout << "T: " << N << " => " << C << "\n";
    return C;
}

int Solution::solve(vector<int> &A) {
    // note: track positional index of cummulative XOR
    // if it has two positions then in between every possible triplets
    // can satisfy the equation
    
    // cummulative XOR positions track
    unordered_map<int, vector<int>> xor_positions;
    int N = A.size();
    int XOR[N + 1];
    XOR[0] = 0;
    xor_positions[0].push_back(0);
    // cummulative XOR positions
    for(int i = 0; i < N; i++) {
        int idx = i + 1;
        XOR[idx] = XOR[i] ^ A[i];
        xor_positions[XOR[idx]].push_back(idx);
    }
    // count triplets for each XOR
    ll C = 0;
    unordered_map<int, vector<int>>::iterator positions_i;
    for(positions_i = xor_positions.begin(); positions_i != xor_positions.end(); positions_i++) {
        C += count(positions_i->second);
    }
    return C % MOD;
}
