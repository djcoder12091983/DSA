int Solution::solve(const vector<int> &A, const vector<int> &B, const vector<int> &C) {
    int max_eating = INT_MIN;
    int N = A.size();
    // find max eating capcity, based on that we will build table
    unordered_map<int, int> eating_capacities;
    for(int i = 0; i < N; i++) {
        max_eating = max(max_eating, A[i]);
        eating_capacities[A[i]]++;
    }
    N = B.size();
    int DP[max_eating + 1];
    for(int i = 0; i <= max_eating; i++) {
        DP[i] = INT_MAX;
    }
    // base cases
    DP[0] = 0;
    
    // fill the table top down manner
    for(int i = 1; i <= max_eating; i++) {
        for(int j = 0; j < N; j++) {
            int req = i - B[j];
            if(req >= 0 && DP[req] != INT_MAX) {
                DP[i] = min(DP[i], C[j] + DP[req]);
            }
        }
    }
    // check minimum cost for all eating capcities
    int min_cost = 0;
    for(int i = 1; i <= max_eating; i++) {
        if(eating_capacities.find(i) != eating_capacities.end()) {
            int f = eating_capacities[i];
            min_cost += f * DP[i]; // add minimum cost
        }
    }
    return min_cost;
}