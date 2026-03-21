// standard BS
bool bs(vector<int> &A, int start, int end, int X) {
    int l = start, r = end;
    bool ok = false;
    while(l <= r) {
        int mid = (l + r) / 2;
        if(A[mid] == X) {
            ok = true;
            break;
        } else if(A[mid] < X) {
            // take right
            l = mid + 1;
        } else {
            // take left
            r = mid - 1;
        }
    }
    return ok;
}

int Solution::diffPossible(vector<int> &A, int B) {
    // using BS
    // TODO need to use two pointers technique
    int N = A.size();
    bool ok = false;
    for(int i = 0; i < N - 1; i++) {
        int req = A[i] + B;
        bool f = bs(A, i + 1, N - 1, req);
        if(f) {
            // found
            ok = true;
            break;
        }
    }
    
    return ok;
}
