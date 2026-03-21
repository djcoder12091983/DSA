class Solution {
public:
    int maxArea(vector<int>& A) {
        
        int l = 0, r = A.size() - 1;
        int ans = 0;
        while(l < r) {
            ans = max(ans, min(A[l], A[r]) * (r - l));
            if(A[l] < A[r]) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
};