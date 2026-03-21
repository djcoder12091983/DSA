#define MODULO 1000000007

typedef long long int LL;

int Solution::cntBits(vector<int> &A) {
    // positional bit set/reset count
    LL positional_bits[31][2]; // avoid signed part
    for(int i = 0; i < 31; i++) {
        positional_bits[i][0] = positional_bits[i][1] = 0L;
    }
    
    for(int a : A) {
        for(int i = 0; i < 31; i++) {
            bool b1 = a & (1 << i);
            positional_bits[i][0] += !b1; // reset bit
            positional_bits[i][1] += b1; // set bit
        }
    }
    
    // sum of differences
    LL sum = 0;
    for(int i = 0; i < 31; i++) {
        // ordered pair
        sum += 2L * (positional_bits[i][0] * positional_bits[i][1]);
        if(sum >= MODULO) {
            // modulo reduction
            sum %= MODULO;
        }
    }
    
    return sum % MODULO;
}
