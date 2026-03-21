// keep negative numbers on left and positives on right
int sort(vector<int> &A, int N) {
    int j = 0;
    for(int i = 0; i < N; i++) {
        if(A[i] <= 0) {
            swap(A[i], A[j]); 
            j++;
        }
    }
    // where positive starts
    return j;
}

int missing_positive(vector<int> &A, int start_idx, int N) {
    int max_a = N - start_idx;
    int i = start_idx;
    while(i < N) {
        int a = A[i];
        if(a <= max_a && A[i] != i - start_idx + 1) {
            // valid numbers to arrange
            int req_idx = a + start_idx - 1;
            if(A[i] == A[req_idx]) {
                // already processed, so skip
                i++;
            } else {
                swap(A[i], A[req_idx]);
            }
        } else {
            i++;
        }
    }
    i = start_idx;
    for(; i < N; i++) {
        if(A[i] != i - start_idx + 1) {
            // first integer missing
            break;
        }
    }
    return i - start_idx + 1;
}

int Solution::firstMissingPositive(vector<int> &A) {
    int N = A.size();
    int start_idx = sort(A, N);
    return missing_positive(A, start_idx, N);
}