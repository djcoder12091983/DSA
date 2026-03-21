class Solution {
public:
    int change(int A, vector<int>& C) {
        int DP[A+1];
        memset(DP, 0, sizeof(DP));
        DP[0] = 1;
        int m = C.size();
        for(int i = 0; i < m; i++) {
            for(int j = C[i]; j <= A; j++) {
                DP[j] += DP[j-C[i]];
            }
        }
  
        return DP[A];
    }
};