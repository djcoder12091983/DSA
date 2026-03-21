#include <bits/stdc++.h>
using namespace std;
 
typedef unsigned long long int UL;
 
UL area(UL A[], int n) {
    stack<int> s;
    UL max = 0;
    int i;
    for(i = 0; i < n; i++) {
        if(s.empty() || A[i] >= A[s.top()]) {
            s.push(i); // insert index
        } else {
            // calculating area
            while(!s.empty() && A[i] < A[s.top()]) {
                int top = s.top();
                s.pop(); // remove element
                UL a;
                if(s.empty()) {
                    a = A[top] * i;
                } else {
                    a = A[top] * (i - s.top() - 1);
                }
                if(a > max) {
                    // max area
                    max = a;
                }
            }
            s.push(i);
        }
    }
    
    // remaining part
    while(!s.empty()) {
        int top = s.top();
        s.pop(); // remove element
        UL a;
        if(s.empty()) {
            a = A[top] * i;
        } else {
            a = A[top] * (i - s.top() - 1);
        }
        if(a > max) {
            // max area
            max = a;
        }
    }
    cout << "\n";
    
    return max;
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
    
    int t, n;
    cin >> t;
    while(t--) {
        cin >> n;
        UL A[n];
        for(int i = 0; i < n; i++) {
            cin >> A[i];
        }
        
        cout << area(A, n) << "\n";
    }
    
    return 0;
}