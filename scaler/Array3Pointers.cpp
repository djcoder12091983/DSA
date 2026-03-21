int Solution::minimize(const vector<int> &A, const vector<int> &B, const vector<int> &C) {
    // ref: <geeksforgeeks>/find-three-closest-elements-from-given-three-sorted-arrays/
    int N1 = A.size(), N2 = B.size(), N3 = C.size();
    int ans = INT_MAX;
    for(int i = 0, j = 0, k = 0; i < N1 && j < N2 && k < N3;) {
        int minimum = min(A[i], min(B[j], C[k]));
        int maximum = max(A[i], max(B[j], C[k]));
        
        int t = maximum - minimum;
        if(t < ans) {
            ans = t;
        }
        
        if(ans == 0) {
            // no minimization possible
            break;
        }
        
        // now move pointer accordingly
        if(A[i] == minimum) {
            i++;
        } else if(B[j] == minimum) {
            j++;
        } else {
            k++;
        }
    }
    return ans;
}
