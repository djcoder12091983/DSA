#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include<limits.h>
 
// best index with maximum sum
long best_index_max(long long int p_sum[], int n) {
    int l, next_i, i;
    long long int s, max = LONG_MIN;
    
    for(i = 1; i <= n; i++) {
        // check every index to get maximum sum
        // now the trick is for every i what is the sub-array length to get sum
        // to solve this i have an inequality equation
        // i + l*(l + 1) / 2 - 1 <= n
        // solving this equation i have l <= (-1 + sqrt(1 + 8*(n - i + 1))) / 2
        // note: avoiding negative root
        l = (-1 + sqrt(1 + 8 * (n - i + 1))) / 2;
        if(l == 0) {
            // only index itself
            next_i = i;
        } else {
            // otherwise i + ap_sum(l) - 1
            next_i = i + (l * (l + 1)) / 2 - 1;
        }
        
        s = p_sum[next_i] - p_sum[i - 1]; // sub-array sum
        if(s > max) {
            // new max
            max = s;
        }
    }
    
    return max;
}
 
int main() {
    
    // read inputs
    int n, a, i;
    long long int *p_sum;
    
    scanf("%d", &n);
    p_sum = (long*)malloc((n + 1) * sizeof(long long int));
    p_sum[0] = 0;
    
    // prefix sum calculation
    for(i = 0; i < n; i++) {
        scanf("%d", &a);
        // prefix sum
        p_sum[i + 1] = p_sum[i] + a;
    }
    
    printf("%ld", best_index_max(p_sum, n));
    
    return 0;
}