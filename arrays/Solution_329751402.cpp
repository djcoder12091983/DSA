class Solution {
private:
    // simple binary search
    int b_search(vector<int>& A, int start, int end, int target) {
        int l = start, r = end;
        int idx = -1;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(A[mid] == target) {
                // found
                idx = mid;
                break;
            } else if(A[mid] > target) {
                // take left
                r = mid - 1;
            } else {
                // take right
                l = mid + 1;
            }
        }
        return idx;
    }
public:
    int search(vector<int>& A, int target) {
        int N = A.size();
        int l = 0, r = N - 1;
        int idx = -1;
        // binary search with tricks
        while(l <= r) {
            int mid = (l + r) / 2;
            if(A[mid] == target) {
                // found
                idx = mid;
                break;
            }
            if(A[l] <= A[mid]) {
                if(A[l] <= target && A[mid] >= target) {
                    // simple binary search on this range
                    idx = b_search(A, l, mid - 1, target);
                    break;
                } else {
                    // continue with right side
                    l = mid + 1;
                }
            } else {
                if(A[mid] <= target && A[r] >= target) {
                    // simple binary search on this range
                    idx = b_search(A, mid + 1, r, target);
                    break;
                } else {
                    // continue with left side
                    r = mid - 1;
                }
            }
        }
        return idx;
    }
};