// recursive DP approach
int min_count(int x, int DP[]) {
    if(x == 0) {
        return 0; // base case
    }
    if(DP[x] != -1) {
        // pre computed value
        return DP[x];
    }
    int min_c = x;
    int i = 1, sqr;
    while((sqr = i * i) <= x) {
        int rem = x - sqr;
        if(rem >= 0) {
            // recursive call with remaining
            min_c = min(min_c, 1 + min_count(rem, DP));
        } 
        i++;
    }
    DP[x] = min_c;
    return min_c;
}

int Solution::countMinSquares(int A) {
    int DP[A + 1];
    memset(DP, -1, sizeof(DP));
    return min_count(A, DP);
}
