class Solution {
public:
    int singleNonDuplicate(vector<int>& A) {
        // do BS
        int N = A.size();
        if(N == 1) {
            // best case
            return A[0];
        }
        int l = 0, r = N - 1;
        while(l <= r) {
            int mid = (l + r) / 2;
            int right_l = N - 1 - mid;
            if(right_l & 1) {
                // odd length
                if(A[mid + 1] == A[mid]) {
                    // take left side
                    r = mid - 1;
                } else {
                    // take right side
                    l = mid + 1;
                }
            } else {
                // even length
                if(A[mid + 1] == A[mid]) {
                    // take right side
                    l = mid + 1;
                } else {
                    // take left side
                    r = mid - 1;
                }
            }
        }
        return A[l]; // last index, no where to go
    }
};