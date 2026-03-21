bool comp(pair<int, int> d1, pair<int, int> d2) {
    if(d1.first == d2.first) {
        return d1.second < d2.second;
    }
    return d1.first < d2.first;
}

int Solution::candy(vector<int> &A) {
    int N = A.size();
    pair<int, int> data[N];
    for(int i = 0; i < N; i++) {
        data[i] = {A[i], i};
    }
    // sort data along with index to check whether they are neighbours or not
    sort(data, data + N, comp);
    
    int distributed[N]; // candy distribution
    memset(distributed, 0, sizeof(distributed)); // init
    int c = 0;
    for(int i = 0; i < N; i++) {
        int idx = data[i].second;
        int rank = data[i].first;
        int max_c = 0;
        bool same = true;
        if(idx - 1 >= 0 && distributed[idx - 1]) {
            // check on left side
            if(rank != A[idx - 1]) {
                max_c = max(max_c, distributed[idx - 1]);
                // check rank wrt neighbours
                same = false;
            }
        }
        if(idx + 1 < N && distributed[idx + 1]) {
            // check on right side
            if(rank != A[idx + 1]) {
                max_c = max(max_c, distributed[idx + 1]);
                // check rank wrt neighbours
                same = false;
            }
        }
        if(!same) {
            // have more priority than neighbours
            max_c++; // more candy than neighbours
        } else {
            max_c = 1; // at least one candy
        }
        distributed[idx] = max_c; // distribute candy
        c += max_c;
    }
    return c;
}
