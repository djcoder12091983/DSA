#define MOUDLO 1000000007
typedef long long int LL;

LL merge_and_count(vector<int> &A, int l, int mid, int r) {
    int i, j, k;
    LL inv_c = 0L; 
    i = l;
    j = mid;
    // merging two sorted arrays
    vector<int> t; // extra space
    while((i <= mid - 1) && (j <= r)) { 
        if(A[i] > A[j]) {
            t.push_back(A[j++]);
            // this inversion count is required during merging
            inv_c = inv_c + (mid - i);
        } else {
            t.push_back(A[i++]);
        }
        if(inv_c >= MOUDLO) {
            // modulo reduction
            inv_c %= MOUDLO;
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
    return inv_c % MOUDLO; 
}

LL divide_and_count(vector<int> &A, int l, int r) {
    int mid;
    LL inv_c = 0L; 
    if(r > l) {
        mid = (l + r) / 2;
        // divide into two halves and get inversion count from each halves
        inv_c += divide_and_count(A, l, mid);
        if(inv_c >= MOUDLO) {
            // modulo reduction
            inv_c %= MOUDLO;
        }
        inv_c += divide_and_count(A, mid + 1, r);
        if(inv_c >= MOUDLO) {
            // modulo reduction
            inv_c %= MOUDLO;
        }
        // merging and inversion count
        inv_c += merge_and_count(A, l, mid + 1, r);
        if(inv_c >= MOUDLO) {
            // modulo reduction
            inv_c %= MOUDLO;
        }
    } 
    return inv_c % MOUDLO;
}

int Solution::solve(vector<int> &A) {
    // standard approach to get count inversion using divide and conquer
    return divide_and_count(A, 0, A.size() - 1);
}
