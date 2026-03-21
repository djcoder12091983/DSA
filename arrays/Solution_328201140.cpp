class Solution {
public:
    int findDuplicate(vector<int>& A) {
        int N = A.size();
        int dup;
        for(int i = 0; i < N; i++) {
            if(A[abs(A[i])] >= 0) { 
                A[abs(A[i])] = -A[abs(A[i])];
            } else {
                // it would happen @ least once
                dup = A[i];
                break;
            }
        }
        return abs(dup);
    }
};