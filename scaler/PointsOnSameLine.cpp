// hash a pair
struct hash_pair { 
    size_t operator()(const pair<int, int>& p) const {
        int h1 = p.first;
        auto h2 = p.second;
        return h1 ^ h2;
    }
};

int Solution::solve(vector<int> &A, vector<int> &B) {
    int N = A.size();
    if(N <= 2) {
        // best case
         return N;
    }
    
    int maxp = 0; 
    int cur_max, opoints, vpoints;
    
    // try to figure out how many points can be grouped by slope map
    unordered_map<pair<int, int>, int, hash_pair> slope_map;
    
    for(int i = 0; i < N; i++) {
        cur_max = opoints = vpoints = 0;
        int x1 = A[i], y1 = B[i];
        for(int j = i + 1; j < N; j++) {
            int x2 = A[j], y2 = B[j];
            if(x1 == x2 && y1 == y2) {
                // avoid duplicate counts
                opoints++;
            } else if(x1 == x2) {
                // vertical points
                vpoints++;
            } else {
                int y_diff = y2 - y1;
                int x_diff = x2 - x1;
                int cf = __gcd(x_diff, y_diff); 
                // reducing the difference by their gcd 
                x_diff /= cf;
                y_diff /= cf;
                // update current slope mapping
                pair<int, int> slope = {y_diff, x_diff};
                slope_map[slope]++;
                cur_max = max(cur_max, slope_map[slope]);
            }
            cur_max = max(cur_max, vpoints);
        }
        // updating global maximum by current point's maximum 
        maxp = max(maxp, cur_max + opoints + 1); 
  
        slope_map.clear();
    }
    
    return maxp;
}
