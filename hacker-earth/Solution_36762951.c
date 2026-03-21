#include<stdio.h>
#define LIMIT 100
 
int main() {
    
    int t, i, n, k, A[LIMIT + 1], c, C_W, req;
    
    scanf("%d", &t);
    
    while(t--) {
        scanf("%d", &n);
        
        memset(A, 0, (LIMIT + 1) * sizeof(int));
        for(i = 0; i < n; i++) {
            scanf("%d", &c);
            A[c]++; // frequency table
        }
        
        scanf("%d", &k);
 
        // count
        C_W = 0;        
        for(i = 1; i <= LIMIT; i++) {
            req = k - i;
            if(req < i) {
                // limit reached
                break;
            }
            if(req <= LIMIT && A[req]) {
                if(req == i) {
                    // A[i] choose 2 (self pairing)
                    C_W += A[i] * (A[i] - 1) / 2;
                } else {
                    // cross pairing
                    C_W += A[i] * A[req];
                }
            }
        }
        
        // print count
        printf("%d\n", C_W);
    }
    
    return 0;
}