class Solution {
private:
    int msb_pos(int N) {
        int msb_p = -1;
        while (N) {
            N = N >> 1;
            msb_p++;
        }
        return msb_p; 
    }
public:
    int rangeBitwiseAnd(int M, int N) {
        int and_result = 0;
        while(M && N) { 
            // find MSB 
            int msb_p1 = msb_pos(M);
            int msb_p2 = msb_pos(N);
            if (msb_p1 != msb_p2) { 
                break;
            }
            int msb_val =  (1 << msb_p1); 
            and_result += msb_val; 
            M = M - msb_val; 
            N = N - msb_val; 
        } 

        return and_result;
    }
};