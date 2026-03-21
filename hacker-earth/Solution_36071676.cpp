#include <bits/stdc++.h>
using namespace std;
 
// solution
int solve(int BC[], int W[], int n, int m, int k) {
    // sort for greedy search
    sort(BC, BC + n);
    sort(W, W + m);
    
    int fb = 0;
    int i = 0, j = 0; // two pointers
    while(i < n && j < m) {
        int bc = BC[i];
        int w = W[j];
        
        if(bc == w) {
            // best fit without extended capacity
            fb++;
            i++;
            j++;
        } else if(bc < w) {
            // try to use extended capacity
            int bc_e = bc + k;
            if(bc_e >= w) {
                // fit with extended capacity
                fb++;
                i++;
                j++;
            } else {
                // move capacity, capacity can't hold current weight
                i++;
            }
        } else {
            // move wight, weight can't be fit in the box
            j++;
        }
    }
    
    return fb;
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int t, n, m, k;
    cin >> t;
    
    while(--t >= 0) {
        cin >> n >> m >> k;
        int BC[n], W[m];
        
        for(int i = 0; i < n; i++) {
            cin >> BC[i];
        }
        for(int i = 0; i < m; i++) {
            cin >> W[i];
        }
        
        // solve
        cout << solve(BC, W, n, m, k) << "\n";
    }
    
    return 0;
}