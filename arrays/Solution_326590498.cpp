class Solution {
public:
    vector<int> productExceptSelf(vector<int>& A) {
        int N = A.size();
        int left[N], right[N];
        vector<int> P;
        
        left[0] = 1;
        right[N - 1] = 1;
        
        for(int i = 1; i < N; i++) { 
            left[i] = A[i - 1] * left[i - 1];
        }
        for(int i = N - 2; i >= 0; i--) {
            right[i] = A[i + 1] * right[i + 1];
        }
        for(int i = 0; i < N; i++) { 
            P.push_back(left[i] * right[i]);
        }
        return P;
    }
};