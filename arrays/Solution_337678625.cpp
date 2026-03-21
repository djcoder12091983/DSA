typedef long long int LL;

class Solution {
public:
    int maxTurbulenceSize(vector<int>& A) {
        int N = A.size();
        if(N <= 1) {
            return N;
        }
        LL d = A[0] - A[1];
        int max_l = 1;
        int c = 2 * (d != 0L);
        for(int i = 1; i < N - 1; i++) {
            LL t_d = A[i] - A[i + 1];
            if(d * t_d < 0L) {
                // sign flip
                c++;
            } else {
                // seqeunce break
                max_l = max(max_l, c);
                c = 2 * (t_d != 0L);
            }
            d = t_d;
        }
        
        max_l = max(max_l, c);
        
        return max_l;
    }
};