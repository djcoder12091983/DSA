class Solution {
public:
    int maxSubArray(vector<int>& A) {
        int S = 0, max_sum = 0;
        int N = A.size();
        for(int i = 0; i < N; i++) {
            S += A[i];
            if(S <= 0) {
                S = 0;
            } else {
                if(S > max_sum) {
                    max_sum = S;
                }
            }
        }
        if(max_sum == 0) {
            // all are negatives
            max_sum = *max_element(A.begin(), A.end());
        }
        return max_sum;
    }
};