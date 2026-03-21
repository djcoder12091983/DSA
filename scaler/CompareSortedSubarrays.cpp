typedef long long int LL;

vector<int> Solution::solve(vector<int> &A, vector<vector<int> > &B) {
    // prefix sum approach whenever sum is equal then need to check using hashing
    // but test cases are so relxed that's why it would work, otherwise it won't work
    int N = A.size();
    LL prefix_sum[N + 1];
    prefix_sum[0] = 0L;
    for(int i = 0; i < N; i++) {
        prefix_sum[i + 1] = prefix_sum[i] + A[i];
    }
    
    int Q = B.size();
    vector<int> q_ans(Q);
    for(int i = 0; i < Q; i++) {
        int l1 = B[i][0], r1 = B[i][1];
        int l2 = B[i][2], r2 = B[i][3];
        LL s1 = prefix_sum[r1 + 1] - prefix_sum[l1];
        LL s2 = prefix_sum[r2 + 1] - prefix_sum[l2];
        if(s1 == s2) {
            // need to validate
            unordered_map<int, int> f1, f2;
            for(int j = l1; j <= r1; j++) {
                f1[A[j]]++;
            }
            for(int j = l2; j <= r2; j++) {
                f2[A[j]]++;
            }
            bool ok = true;
            for(pair<int, int> detail : f1) {
                int a = detail.first;
                int f = detail.second;
                if(f2.find(a) == f2.end() || f2[a] != f) {
                    // not possible
                    ok = false;
                    break;
                }
            }
            q_ans[i] = ok;
        } else {
            // otherwise straightway 0
            q_ans[i] = 0;
        }
    }
    
    return q_ans;
}
