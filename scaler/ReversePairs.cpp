// almost same as inversion count except comparison logic
int merge_and_count(vector<int> &A, int l, int mid, int r) {
    int i, j, k;
    int inv_c = 0; 
    i = l;
    j = mid;
    // here don't create temporary array rather do inversion count here
    // later section handles merging and copy back to original
    while(i <= mid - 1 && j <= r) { 
        if(A[i] > 2*A[j]) {
            // this condition is required to get expected inversion count
            inv_c = inv_c + (mid - i);
            j++;
        } else {
            i++;
        }
    }
    // merging two sorted arrays
    vector<int> t; // extra space
    i = l;
    j = mid;
    while(i <= mid - 1 && j <= r) {
        if(A[i] > A[j]) {
            t.push_back(A[j++]);
        } else {
            t.push_back(A[i++]);
        }
    }
    // copy remaining
    while(i <= mid - 1) {
        t.push_back(A[i++]);
    }
    while(j <= r) {
        t.push_back(A[j++]);
    }
    // copy into original
    for(i = l; i <= r; i++) {
        A[i] = t[i - l];
    }
    return inv_c; 
}

int divide_and_count(vector<int> &A, int l, int r) {
    int mid;
    int inv_c = 0; 
    if(r > l) {
        mid = (l + r) / 2;
        // divide into two halves and get inversion count from each halves
        inv_c += divide_and_count(A, l, mid);
        inv_c += divide_and_count(A, mid + 1, r);
        // merging and inversion count
        inv_c += merge_and_count(A, l, mid + 1, r);
    } 
    return inv_c;
}

int Solution::solve(vector<int> &A) {
    return divide_and_count(A, 0, A.size() - 1);
}
