int Solution::solve(vector<int> &A) {
    // note: when an element has left bigger element then look take left
    // when an element has right bigger element then look take left
    // this logic works when unique answer available
    int N = A.size();
    int l = 0, r = N - 1;
    int peak;
    while(l <= r) {
        int mid = (l + r) / 2;
        if((mid == 0 || A[mid - 1] <= A[mid])  
            && (mid == N - 1 || A[mid + 1] <= A[mid])) {
                // found
                peak = A[mid];
                break;
            }
        else if(mid > 0 && A[mid - 1] > A[mid]) {
            // take left
            r = mid - 1;
        } else {
            // take right
            l = mid + 1;
        }
    }
    return peak;
}
