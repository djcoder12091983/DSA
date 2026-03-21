class Solution {
public:
    int subarraySum(vector<int>& A, int K) {
        // prefix sum map
        int N = A.size();
        unordered_map<int, int> prefix_sum;
        int S = 0;
        for(int i = 0; i < N; i++) {
            S += A[i];
            prefix_sum[S]++;
        }
        // try to find sequences having sum=K
        S = 0;
        int C = 0;
        for(int i = 0; i < N; i++) {
            int req = S + K;
            if(prefix_sum.find(req) != prefix_sum.end()) {
                // sequence sum exists
                C += prefix_sum[req];
            }
            // remove current sum to avoid duplicates count
            S += A[i];
            prefix_sum[S]--;
            if(prefix_sum[S] == 0) {
                prefix_sum.erase(S);
            }
        }
        return C;
    }
};