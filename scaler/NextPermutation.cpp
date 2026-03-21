void reverse(vector<int> &A, int l, int r) {
    while (l < r) {
        swap(A[l++], A[r--]);
    }
}

int binary_search(vector<int> &A, int l, int r, int key) {
    int index = -1;
    while(l <= r) {
        int mid = l + (r - l) / 2;
        if(A[mid] <= key) {
            r = mid - 1;
        } else {
            l = mid + 1;
            if(index == -1 || A[index] >= A[mid]) {
                index = mid;
            }
        }
    }
    return index;
}

vector<int> Solution::nextPermutation(vector<int> &A) {
    int l = A.size();
    int i = l - 2;
    while(i >= 0 && A[i] >= A[i + 1]) {
        --i;
    }
    if(i < 0) {
        // initial
        sort(A.begin(), A.end());
    } else {
        int idx = binary_search(A, i + 1, l - 1, A[i]);
        swap(A[i], A[idx]);
        reverse(A, i + 1, l - 1);
    }
    return A;
}
