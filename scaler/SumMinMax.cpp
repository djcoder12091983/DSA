// note: <geeksforgeeks>/sum-minimum-maximum-elements-subarrays-size-k/
int Solution::solve(vector<int> &A, int B) {
    int sum = 0;
    // G mainatins decreasing and S maintains increasing
    deque< int > S(B), G(B);
    
    // first window
    int i = 0;
    while(i < B) {
        // remove from rear previous greater elements 
        while(!S.empty() && A[S.back()] >= A[i]) {
            S.pop_back();
        }
        // remove from erar previous smaller elements 
        while(!G.empty() && A[G.back()] <= A[i]) {
            G.pop_back();
        }
        // add current
        G.push_back(i);
        S.push_back(i);
        i++;
    }
    int modulo = 1000000007;
    int N = A.size();
    while(i < N) {
        // current max and min
        sum += A[S.front()] + A[G.front()];
        if(sum < 0) {
            sum += modulo;
        } else {
            sum %= modulo;
        }
        // avoid window overflow
        while(!S.empty() && S.front() <= i - B) {
            S.pop_front();
        }
        while(!G.empty() && G.front() <= i - B) {
            G.pop_front();
        }
        // remove previous greater elements
        while(!S.empty() && A[S.back()] >= A[i]) {
            S.pop_back();
        }
        // remove previous smaller elements
        while(!G.empty() && A[G.back()] <= A[i]) {
            G.pop_back();
        }
        // add current 
        G.push_back(i);
        S.push_back(i);
        i++;
    }
    // last window
    sum += A[S.front()] + A[G.front()];
    if(sum < 0) {
        sum += modulo;
    } else {
        sum %= modulo;
    }
    return sum;
}