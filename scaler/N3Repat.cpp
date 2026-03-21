int Solution::repeatedNumber(const vector<int> &A) {
    // ref: <geeks4geeks>/n3-repeated-number-array-o1-space/
    // use moor voting algo using two pointer
    int a1 = INT_MAX, a2 = INT_MAX;
    int c1 = 0, c2 = 0;
    int N = A.size();
    for(int i = 0; i < N; i++) {
        if(A[i] == a1) {
            c1++;
        } else if(A[i] == a2) {
            c2++;
        } else if(c1 == 0) {
            a1 = A[i];
            c1++;
        } else if(c2 == 0) {
            a2 = A[i];
            c2++;
        } else {
            c1--;
            c2--;
        }
    }
    c1 = 0, c2 = 0;
    // now check which one occurs more than N/3
    for(int i = 0; i < N; i++) {
        if(A[i] == a1) {
            c1++;
        } else if(A[i] == a2) {
            c2++;
        }
    }
    if(c1 > N/3) {
        return a1;
    } else if(c2 > N/3) {
        return a2;
    } else {
        // not exists
        return -1;
    }
}
