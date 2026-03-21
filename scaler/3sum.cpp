typedef long long int LL;

int Solution::threeSumClosest(vector<int> &A, int B) {
    // ref: <geeksforgeeks>/find-a-triplet-in-an-array-whose-sum-is-closest-to-a-given-number/
    
    sort(A.begin(), A.end()); // sort to find closest numbers
    LL X = B;
    
    int N = A.size();
    LL closed_sum = INT_MAX;
    for(int i = 0; i < N - 2; i++) {
        // move two pointers inside [i+1, N - 1]
        int p1 = i + 1, p2 = N - 1;
        while(p1 < p2) {
            LL sum = A[i] + A[p1] + A[p2];
            LL d1 = abs(X - sum), d2 = abs(X - closed_sum);
            if(d1 < d2) {
                // new closed sum
                closed_sum = sum;
            }
            
            // move pointer accordingly
            if(sum > X) {
                p2--;
            } else {
                p1++;
            }
        }
    }
    
    return closed_sum;
}
