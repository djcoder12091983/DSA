#include<stdio.h>
 
int main() {
    
    int N , M;
    long long int A[100001], w, min_t;
    int i, u, v;
    
    // start with 0 balance
    memset(A, 0, 100001 * sizeof(long long int));
    
    scanf("%d%d", &N, &M);
    for(i = 0; i < M; i++) {
        scanf("%d%d%ld", &u, &v, &w);
        
        // update credits and debits
        A[u] -= w; // debit
        A[v] += w; // credit
    }
    
    // now check all positive balance
    // note debit will definite equals to credit, no need to cross check
    min_t = 0;
    for(i = 1; i <= N; i++) {
        if(A[i] > 0) {
            min_t += A[i];
        }
    }
    
    printf("%ld", min_t);
    
    return 0;
}