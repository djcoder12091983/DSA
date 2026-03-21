#define MOUDLO 10000003
 
typedef long long int LL;
 
// whether for given time painters can paint boards
bool possible(vector<int> &C, int N, int A, LL length) {
    int p = 0;
    LL t = 0L;
    for(int i = 0; i < N; i++) {
        t += C[i];
        if(t > length) {
            // need more painters
            p++;
            if(p > A) {
                // painters overflow
                return false;
            }
            // reset
            t = C[i]; 
        }
    }
    p++;
    return p <= A;
}
 
int Solution::paint(int A, int B, vector<int> &C) {
    // again almost simliar to agressive cow and page distribution problem
    // BS search space [max_length, total_length]
    LL total_l = 0L;
    int N = C.size();
    int max_l = 0L;
    for(int i = 0; i < N; i++) {
        total_l += C[i];
        max_l = max(max_l, C[i]);
    }
    
    LL l = max_l, r = total_l;
    LL min_l = r;
    while(l <= r) {
        LL length = (l + r) / 2;
        bool ok = possible(C, N, A, length);
        if(ok) {
            // try to find closer length
            // give chances other painters to minimize total cost
            min_l = length;
            r = length - 1; // take left
        } else {
            // need to increase length (take right)
            l = length + 1;
        }
    }
    
    return (min_l * B) % MOUDLO; // modulo reduction
}