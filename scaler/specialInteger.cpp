typedef long long int LL;

int Solution::solve(vector<int> &A, int B) {
    // prefix sum and BS will work together
    int N = A.size();
    LL bigger_b = B;
    LL prefix_sum[N + 1];
    prefix_sum[0] = 0L;
    
    // prefix sum
    for(int i = 0; i < N; i++) {
        LL a = A[i];
        prefix_sum[i + 1] = prefix_sum[i] + a;
    }
    if(prefix_sum[N] - prefix_sum[0] <= B) {
        // full length
        return N;
    }
    // try to solve it using sliding window approach
    int i = 0, j = 0, min_w = INT_MAX;
    while(i <= N && j <= N) {
        LL sum = prefix_sum[j] - prefix_sum[i];
        if(sum <= B) {
            // valid subarray sum
            j++;
        } else {
            // not a valid subarray
            // save min of all window size
            int window = j - i - 1;
            min_w = min(min_w, window);
            i++;
        }
    }
    return min_w;
}
