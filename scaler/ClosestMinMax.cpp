int Solution::solve(vector<int> &A) {
    int min_a = *min_element(A.begin(), A.end());
    int max_a = *max_element(A.begin(), A.end());
    if(min_a == max_a) {
        // all elements are same
        return 1;
    }
    vector<int> min_p, max_p;
    int l = A.size();
    for(int i = 0; i < l; i++) {
        if(A[i] == min_a) {
            min_p.push_back(i);
        } else if(A[i] == max_a) {
            max_p.push_back(i);
        }
    }
    int min_l = INT_MAX;
    l = max_p.size();
    for(int p : min_p) {
        int p1 = lower_bound(max_p.begin(), max_p.end(), p) - max_p.begin();
        int p2 = upper_bound(max_p.begin(), max_p.end(), p) - max_p.begin();
        if(p1 - 1 >= 0) {
            min_l = min(min_l, abs(p - max_p[p1 - 1]) + 1);
        }
        if(p2 < l) {
            min_l = min(min_l, abs(p - max_p[p2]) + 1);
        }
    }
    return min_l;
}
