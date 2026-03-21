// whether cows can be placed optimally for a given distance
bool possible(vector<int> &A, int N, int dist, int B) {
    int cow = 1; // cow @ first index
    int last = A[0];
    for(int i = 1; i < N; i++) {
        int d = A[i] - last;
        if(d >= dist) {
            // need more cow
            cow++;
            last = A[i];
            if(cow == B) {
                // required cows already fit
                return true;
            }
        }
    }
    return false;
}

int Solution::solve(vector<int> &A, int B) {
    // note: almost similar to page load distribution
    
    int N = A.size();
    if(B > N) {
        // straight way 0, by pegion hole principle
        return 0;
    }
    
    sort(A.begin(), A.end()); // sort to compute distance
    // BS over [1, max - min]
    int l = 1, r = A[N - 1] - A[0];
    int max_d = 0;
    while(l <= r) {
        int dist = (l + r) / 2;
        bool ok = possible(A, N, dist, B);
        if(ok) {
            // if possible then try to increase distance to minimize agressiveness
            // take right side
            l = dist + 1;
            max_d = dist;
        } else {
            // decrease distance to fit
            // take left side
            r = dist - 1;
        }
    }
    return max_d;
}
