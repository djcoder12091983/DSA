#include <bits/stdc++.h>
using namespace std;
 
int solve(int A[], int B[], int n) {
    // if already all are same
    bool f = true;
    for(int i = 0; i < n - 1; i++) {
        if(A[i] != A[i + 1]) {
            // different elements
            f = false;
            break;
        }
    }
    if(f) {
        // no steps required
        return 0;
    }
    
    // try reducing elements by B(i)
    // min element
    int min = *min_element(A, A + n);
    
    // starts with min and try upto 0
    // greedy approach
    for(int i = min; i >= 0; i--) {
        int c = 0;
        for(int j = 0; j < n; j++) {
            if(A[j] >= B[j]) {
                if(B[j] > 0) {
                    // transformation can be done
                    int d = A[j] - i;
                    if(d % B[j] == 0) {
                        c += d / B[j];
                    } else {
                        // not possible with this i
                        c = -1;
                        break;
                    }
                }
            }
        }
        
        if(c == 0) {
            // all elements are not same
            // but c is still 0, so not possible
            return -1;
        } else if(c != -1) {
            // result found
            return c;
        }
    }
    
    return -1; // if not possible yet
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    // read inputs
    int n;
    cin >> n;
    
    int A[n], B[n];
    for(int i = 0; i < n; i++) {
        cin >> A[i];
    }
    for(int i = 0; i < n; i++) {
        cin >> B[i];
    }
    
    // solve
    cout << solve(A, B, n);
    
    return 0;
}